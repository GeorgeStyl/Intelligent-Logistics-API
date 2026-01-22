package org.stylianopoulos.logistics.controller.api;

import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.dto.VehicleRequestDTO;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.service.factory.LogisticsVehicleFactory;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/vehicles/init")
public class VehicleController {

    private final LogisticsVehicleFactory vehicleFactoryManager;

    public VehicleController(LogisticsVehicleFactory vehicleFactoryManager) {
        this.vehicleFactoryManager = vehicleFactoryManager;
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody VehicleRequestDTO request) {
        // ? The factory creates the specific implementation (Drone, etc.)
        return vehicleFactoryManager.createVehicle(
                request.type(),
                request.licensePlate(),
                request.capacity(),
                request.speed()
        );
    }


    @GetMapping("/create-async")
    public CompletableFuture<Vehicle> createVehicleAsync(
            @RequestParam String type,
            @RequestParam String licensePlate,
            @RequestParam int capacity,
            @RequestParam int speed
    ) {
        return CompletableFuture.supplyAsync(() ->
                vehicleFactoryManager.createVehicle(type, licensePlate, capacity, speed)
        );
    }
}