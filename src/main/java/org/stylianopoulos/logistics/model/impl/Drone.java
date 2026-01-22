package org.stylianopoulos.logistics.model.impl;

import org.stylianopoulos.logistics.model.Vehicle;

public class Drone extends Vehicle {

    public Drone(String type, String licensePlate) {
        super(type, licensePlate);
    }

    @Override
    public String getVehicleType() {
        return getInternalType();
    }
}