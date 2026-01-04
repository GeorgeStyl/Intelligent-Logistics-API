package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.model.Drone;
import org.stylianopoulos.logistics.model.Truck;
import org.stylianopoulos.logistics.model.Van;
import org.stylianopoulos.logistics.model.abstraction.Vehicle;
import org.springframework.stereotype.Service;


@Service
public class LogisticsVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type, int capacity, int speed) {
        // * Strategy: Map the string type to the correct concrete object
        return switch (type.toLowerCase()) {
            case "truck" -> new Truck(type, capacity, speed);
            case "van" -> new Van(type, capacity, speed);
            case "drone" -> new Drone(type, capacity, speed);
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }
}