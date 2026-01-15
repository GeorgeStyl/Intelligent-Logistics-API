package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.model.impl.Drone;
import org.stylianopoulos.logistics.model.impl.Truck;
import org.stylianopoulos.logistics.model.impl.Van;
import org.stylianopoulos.logistics.model.Vehicle;
import org.springframework.stereotype.Service;


@Service
public class LogisticsVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(int id, String type, int capacity, int speed) {
        // * Strategy: Map the string type to the correct concrete object
        return switch (type.toLowerCase()) {
            case "truck" -> new Truck(id, type, capacity, speed);
            case "van" -> new Van(id, type, capacity, speed);
            case "drone" -> new Drone(id, type, capacity, speed);
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }
}