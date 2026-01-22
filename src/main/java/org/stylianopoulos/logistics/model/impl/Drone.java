package org.stylianopoulos.logistics.model.impl;

import org.stylianopoulos.logistics.model.Vehicle;

public class Drone extends Vehicle {

    public Drone(String type, String licensePlate, int capacity, int speed) {
        super(type, licensePlate, capacity, speed);
    }

    @Override
    public String getVehicleType() {
        return getInternalType();
    }

    @Override
    public String getVehicleLicensePlate() {
        return getInternalLicensePlate();
    }
}