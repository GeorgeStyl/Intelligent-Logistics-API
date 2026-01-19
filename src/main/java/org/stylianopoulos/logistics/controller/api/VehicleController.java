package org.stylianopoulos.logistics.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.dto.VehicleRequestDTO;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.service.factory.LogisticsVehicleFactory;
import org.stylianopoulos.logistics.service.factory.VehicleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * @RestController marks this class as a handler for REST requests.
 * * All return objects are automatically serialized to JSON.
 */
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    static int id = 0;

    private final VehicleFactory vehicleFactory;
    private final List<Vehicle> database = new ArrayList<>();

    public VehicleController(VehicleFactory vehicleFactory) {
        this.vehicleFactory = vehicleFactory;
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody VehicleRequestDTO request) {
        // * The controller delegates the creation to the Factory Manager
        Vehicle vehicle = LogisticsVehicleFactory.createVehicle(
                request.capacity(),
                request.speed()
        );

        return ResponseEntity.ok(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() { return database; }


    // ! VEHICLE CREATION ENDPOINT
    /**
     * * Creates a vehicle based on query parameters.
     * * Returning the abstraction  allows polymorphic responses (Truck, Drone, Van).
     */
    @GetMapping("/create")
    public Vehicle createVehicle(
            @RequestParam int capacity,
            @RequestParam int speed) {

        // * The controller does not know how to make a Truck => it asks the Factory.
        return vehicleFactory.createVehicle(capacity, speed);
    }
}