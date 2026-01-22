package org.stylianopoulos.logistics.service.factory;

import org.springframework.stereotype.Component;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.model.impl.Van;

@Component
public class VanFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type, String licensePlate,  int  capacity, int speed) {
        return new Van(type, licensePlate,  capacity, speed);
    }

    @Override
    public String getVehicleType() {
        return "VAN";
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