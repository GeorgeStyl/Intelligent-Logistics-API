package org.stylianopoulos.logistics.model.impl;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.stylianopoulos.logistics.model.Vehicle;


@Entity
@DiscriminatorValue("DRONE")
public class Drone extends Vehicle {

    public Drone() {} // ! JPA requirement

    public Drone(String type, String licensePlate, int capacity, int speed) {
        super(type, licensePlate, capacity, speed);
    }

    @Override public String getVehicleType() { return "DRONE"; }
    @Override public String getVehicleLicensePlate() { return getInternalLicensePlate(); }
    @Override public int getCapacity() { return getInternalCapacity(); }
    @Override public int getSpeed() { return getInternalSpeed(); }
}