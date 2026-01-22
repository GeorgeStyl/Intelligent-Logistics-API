package org.stylianopoulos.logistics.model.impl;

import org.stylianopoulos.logistics.model.Vehicle;

public class Van extends Vehicle {

    public Van(String type, String licensePlate) {
        super(type, licensePlate);
    }

    @Override
    public String getVehicleType() {
        return getInternalType();
    }
}