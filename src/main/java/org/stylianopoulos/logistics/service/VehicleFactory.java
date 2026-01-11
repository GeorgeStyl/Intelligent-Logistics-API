package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.model.Vehicle;


public interface VehicleFactory {
    Vehicle createVehicle(String type, int capacity, int speed);
}