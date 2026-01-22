package org.stylianopoulos.logistics.service.factory;

import org.springframework.stereotype.Component;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.model.impl.Truck;

@Component
public class TruckFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type, String licensePlate, int   capacity, int speed) {
        return new Truck(type, licensePlate, capacity, speed);
    }

    @Override
    public String getVehicleType() {
        return "TRUCK";
    }

    @Override
    public String getVehicleLicencePlate() {
        return "";
    }

    @Override
    public String getVehicleCapacity() {
        return "";
    }

    @Override
    public String getVehicleSpeed() {
        return "";
    }
}