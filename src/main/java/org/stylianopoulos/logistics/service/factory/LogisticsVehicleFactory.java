package org.stylianopoulos.logistics.service.factory;

import org.springframework.stereotype.Service;
import org.stylianopoulos.logistics.model.Vehicle;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogisticsVehicleFactory {

    private final Map<String, VehicleFactory> factoryMap;

    public LogisticsVehicleFactory(List<VehicleFactory> factories) {
        this.factoryMap = factories.stream()
                .collect(Collectors.toMap(
                        factory -> factory.getVehicleType().toUpperCase(),
                        factory -> factory
                ));
    }

    public Vehicle createVehicle(int id, String type, String licensePlate) {
        VehicleFactory factory = factoryMap.get(type.toUpperCase());

        if (factory == null) {
            throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }

        return factory.createVehicle(id, type, licensePlate);
    }
}