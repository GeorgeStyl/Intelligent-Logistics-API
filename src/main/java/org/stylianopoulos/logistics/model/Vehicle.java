package org.stylianopoulos.logistics.model;

public abstract class Vehicle {
    private final String type;
    private final String licensePlate;
    private final int capacity;
    private final int speed;

    public Vehicle(String type, String licensePlate, int capacity, int speed) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.speed = speed;
    }

    public abstract String getVehicleType();

    public abstract String getVehicleLicensePlate();

    public abstract int getCapacity();

    public abstract int getSpeed();


    protected String getInternalType() { return type; }
    protected String getInternalLicensePlate() { return licensePlate; }
    protected int getInternalCapacity() { return capacity; }
    protected int getInternalSpeed() { return speed; }
}