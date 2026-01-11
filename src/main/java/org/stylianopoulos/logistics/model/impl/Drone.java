package org.stylianopoulos.logistics.model.impl;

import org.stylianopoulos.logistics.model.Vehicle;


public class Drone extends Vehicle {
    private final String type;
    private final int capacity;
    private final int speed;

    public Drone(String type, int capacity, int speed) {
        this.type = type;
        this.capacity = capacity;
        this.speed = speed;
    }

    @Override
    public String getVehicleType() { return this.type; }

    @Override
    public int getVehicleCapacity() { return this.capacity; }

    @Override
    public int getVehicleSpeed() { return this.speed; }
}
