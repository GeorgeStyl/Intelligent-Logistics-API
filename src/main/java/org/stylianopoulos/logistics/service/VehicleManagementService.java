package org.stylianopoulos.logistics.service;

import org.springframework.stereotype.Service;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.repository.VehicleRepository;
import org.stylianopoulos.logistics.service.factory.LogisticsVehicleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VehicleManagementService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleManagementService.class);
    private final VehicleRepository repository;
    private final LogisticsVehicleFactory vehicleFactory;

    public VehicleManagementService(VehicleRepository repository, LogisticsVehicleFactory vehicleFactory) {
        this.repository = repository;
        this.vehicleFactory = vehicleFactory;
    }

    public void processVehicle(String type, String licensePlate) {
        repository.findByLicensePlate(licensePlate).ifPresentOrElse(
                dbVehicle -> {
                    // * Now accessible because getters are public
                    Vehicle instance = vehicleFactory.createVehicle(
                            dbVehicle.getInternalType(),
                            dbVehicle.getInternalLicensePlate(),
                            dbVehicle.getInternalCapacity(),
                            dbVehicle.getInternalSpeed()
                    );

                    logger.info("Instantiated: {} | Plate: {}", instance.getClass().getSimpleName(), instance.getVehicleLicensePlate());
                },
                () -> { throw new IllegalArgumentException("Vehicle not found: " + licensePlate); }
        );
    }
}