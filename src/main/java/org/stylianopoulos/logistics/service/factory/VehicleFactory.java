package org.stylianopoulos.logistics.service.factory;

import org.stylianopoulos.logistics.model.Vehicle;


public interface VehicleFactory {
    Vehicle createVehicle(int id, String type, int capacity, int speed);
}