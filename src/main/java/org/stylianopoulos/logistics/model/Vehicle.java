package org.stylianopoulos.logistics.model;

import org.springframework.data.annotation.Id;

public abstract class Vehicle {
    @Id
    private int id;
    private String vehicleType;
    private Double capacity;
    private int vehicleSpeed;

    public abstract String getVehicleType();
    public abstract int getVehicleCapacity();
    public abstract int getVehicleSpeed();

    @Override
    public String toString() {
        return String.format("Vehicle [Type=%s, Capacity=%d, Speed=%d]",
                getVehicleType(), getVehicleCapacity(), getVehicleSpeed());
    }
}
