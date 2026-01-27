package org.stylianopoulos.logistics.model.impl;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.stylianopoulos.logistics.model.Vehicle;


@Entity
@DiscriminatorValue("TRUCK")
public class Truck extends Vehicle {
    public Truck() {}
    public Truck(String type, String licensePlate, int capacity, int speed) {
        super(type, licensePlate, capacity, speed);
    }
    @Override public String getVehicleType() { return "TRUCK"; }
    @Override public String getVehicleLicensePlate() { return getInternalLicensePlate(); }
    @Override public int getCapacity() { return getInternalCapacity(); }
    @Override public int getSpeed() { return getInternalSpeed(); }
}