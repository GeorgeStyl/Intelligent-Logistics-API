package org.stylianopoulos.logistics.model.abstraction;


public abstract class Vehicle {
    public abstract String getVehicleType();
    public abstract int getVehicleCapacity();
    public abstract int getVehicleSpeed();

    @Override
    public String toString() {
        return String.format("Vehicle [Type=%s, Capacity=%d, Speed=%d]",
                getVehicleType(), getVehicleCapacity(), getVehicleSpeed());
    }
}
