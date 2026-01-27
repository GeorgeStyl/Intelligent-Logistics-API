package org.stylianopoulos.logistics.model.impl;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.stylianopoulos.logistics.model.Vehicle;


@Entity
@DiscriminatorValue("VAN")
public class Van extends Vehicle {

    public Van() {}
    public Van(String type, String licensePlate, int capacity, int speed) {
        super(type, licensePlate, capacity, speed);
    }
    @Override public String getVehicleType() { return "VAN"; }
    @Override public String getVehicleLicensePlate() { return getInternalLicensePlate(); }
    @Override public int getCapacity() { return getInternalCapacity(); }
    @Override public int getSpeed() { return getInternalSpeed(); }
}