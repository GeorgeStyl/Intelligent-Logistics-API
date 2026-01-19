package org.stylianopoulos.logistics.service.factory;

import org.springframework.stereotype.Component;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.model.impl.Drone;

@Component
public class DroneFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(int id, String type, String licensePlate) {
        return new Drone(id, type, licensePlate);
    }

    @Override
    public String getVehicleType() {
        return "DRONE";
    }
}