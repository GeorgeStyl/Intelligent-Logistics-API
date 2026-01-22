package org.stylianopoulos.logistics.service.factory;

import org.springframework.stereotype.Component;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.model.impl.Van;

@Component
public class VanFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type, String licensePlate) {
        return new Van(type, licensePlate);
    }

    @Override
    public String getVehicleType() {
        return "VAN";
    }
}