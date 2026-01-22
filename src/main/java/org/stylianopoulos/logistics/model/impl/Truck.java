package org.stylianopoulos.logistics.model.impl;

import org.stylianopoulos.logistics.model.Vehicle;

public class Truck extends Vehicle {

    public Truck(String type, String licensePlate) {
        super(type, licensePlate);
    }

    @Override
    public String getVehicleType() {
        return getInternalType();
    }
}