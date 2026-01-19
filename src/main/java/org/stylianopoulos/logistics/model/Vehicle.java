package org.stylianopoulos.logistics.model;

public abstract class Vehicle {
    private final int id;
    private final String type;
    private final String licensePlate;

    public Vehicle(int id, String type, String licensePlate) {
        this.id = id;
        this.type = type;
        this.licensePlate = licensePlate;
    }

    public abstract String getVehicleType();

    // *******************************
    // * PROTECTED ACCESSORS FOR CHILDREN
    // ********************************
    protected int getInternalId() { return id; }
    protected String getInternalType() { return type; }
    protected String getInternalLicensePlate() { return licensePlate; }
}