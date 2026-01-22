package org.stylianopoulos.logistics.service.factory;

import org.springframework.stereotype.Component;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.model.impl.Drone;

@Component
public class DroneFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type, String licensePlate, int  capacity, int speed) {
        return new Drone(type, licensePlate, capacity, speed);
    }

    @Override
    public String getVehicleType() {
        return "DRONE";
    }

    @Override
    public String getVehicleLicencePlate() {
        return "";
    }

    @Override
    public String getVehicleCapacity() {
        return "";
    }

    @Override
    public String getVehicleSpeed() {
        return "";
    }
}