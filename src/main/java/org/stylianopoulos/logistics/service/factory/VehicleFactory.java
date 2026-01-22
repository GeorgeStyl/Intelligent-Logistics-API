package org.stylianopoulos.logistics.service.factory;

import org.stylianopoulos.logistics.model.Vehicle;

public interface VehicleFactory {
    Vehicle createVehicle(String type, String licensePlate, int capacity, int speed);
    String getVehicleType();
    String getVehicleLicencePlate();
    String getVehicleCapacity();
    String getVehicleSpeed();
}