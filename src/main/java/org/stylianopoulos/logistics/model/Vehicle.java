package org.stylianopoulos.logistics.model;

public abstract class Vehicle {
    private final String type;
    private final String licensePlate;

    public Vehicle(String type, String licensePlate) {
        this.type = type;
        this.licensePlate = licensePlate;
    }

    public abstract String getVehicleType();

    // *******************************
    // * PROTECTED ACCESSORS FOR CHILDREN
    // ********************************
    protected String getInternalType() { return type; }
    protected String getInternalLicensePlate() { return licensePlate; }
}