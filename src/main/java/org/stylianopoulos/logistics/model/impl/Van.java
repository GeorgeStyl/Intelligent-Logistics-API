package org.stylianopoulos.logistics.model.impl;

import org.stylianopoulos.logistics.model.Vehicle;

public class Van extends Vehicle {

    public Van(String type, String licensePlate, int  capacity, int speed) {
        super(type, licensePlate, capacity, speed);
    }

    @Override
    public String getVehicleType() {
        return getInternalType();
    }

    @Override
    public String getVehicleLicensePlate() {
        return "";
    }
}