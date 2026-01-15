package org.stylianopoulos.logistics.model.impl;

import org.stylianopoulos.logistics.model.Vehicle;


public class Drone extends Vehicle {
    private final int speed;
    private final String type;
    private final int capacity;

    public Drone(int id, String type, int capacity, int speed) {
        this.speed = speed;
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String getVehicleType() { return this.type; }

    @Override
    public int getVehicleCapacity() { return this.capacity; }

    @Override
    public int getVehicleSpeed() {
        return 0;
    }


}
