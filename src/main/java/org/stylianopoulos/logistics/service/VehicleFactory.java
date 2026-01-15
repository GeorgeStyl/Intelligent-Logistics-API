package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.model.Vehicle;


public interface VehicleFactory {
    Vehicle createVehicle(int id, String type, int capacity, int speed);
}