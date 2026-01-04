package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.model.abstraction.Vehicle;


public interface VehicleFactory {
    Vehicle createVehicle(String type, int capacity, int speed);
}