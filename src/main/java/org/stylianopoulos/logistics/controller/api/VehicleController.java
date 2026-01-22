package org.stylianopoulos.logistics.controller.api;

import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.service.factory.LogisticsVehicleFactory;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final LogisticsVehicleFactory vehicleFactoryManager;

    public VehicleController(LogisticsVehicleFactory vehicleFactoryManager) {
        this.vehicleFactoryManager = vehicleFactoryManager;
    }

    @GetMapping("/create-async")
    public CompletableFuture<Vehicle> createVehicleAsync(
            @RequestParam String type,
            @RequestParam String licensePlate
    ) {

        return CompletableFuture.supplyAsync(() ->
                vehicleFactoryManager.createVehicle(type, licensePlate)
        );
    }
}