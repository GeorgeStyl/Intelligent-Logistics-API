package org.stylianopoulos.logistics.service;

import org.springframework.stereotype.Service;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.repository.VehicleRepository;
import org.stylianopoulos.logistics.service.factory.LogisticsVehicleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class VehicleManagementService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleManagementService.class);
    private final VehicleRepository repository;
    private final LogisticsVehicleFactory vehicleFactory;

    public VehicleManagementService(VehicleRepository repository, LogisticsVehicleFactory vehicleFactory) {
        this.repository = repository;
        this.vehicleFactory = vehicleFactory;
    }

    public List<Vehicle> processVehicle(String type) {
        //**********************************************
        // * STREAM API PROCESSING
        // ? RETURN AT RANDOM
        //*********************************************
        return Collections.singletonList(repository.findByType(type.toUpperCase())
                .stream()
                .map(dbVehicle -> vehicleFactory.createVehicle(
                        dbVehicle.getInternalType(),
                        dbVehicle.getInternalLicensePlate(),
                        dbVehicle.getInternalCapacity(),
                        dbVehicle.getInternalSpeed()
                ))
                // ! Choose 1 at random
                .sorted((a, b) -> new Random().nextInt(3) - 1)
                .findAny() // ! Returns Optional<Vehicle>
                .orElseThrow(() -> new IllegalArgumentException("No vehicles available for type: " + type)));
    }
}