package org.stylianopoulos.logistics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.service.VehicleFactory;

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

    private final VehicleFactory vehicleFactory;
    private final List<Vehicle> database = new ArrayList<>();

    public VehicleController(VehicleFactory vehicleFactory) {
        this.vehicleFactory = vehicleFactory;
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Map<String, Object> payload) {
        String type = (String) payload.get("type");

        // Convert Double (from JSON) to Integer (for Factory)
        int capacity = ((Number) payload.get("capacity")).intValue();
        int speed = ((Number) payload.get("speed")).intValue();

        // Now the types match your Factory signature
        Vehicle newVehicle = vehicleFactory.createVehicle(type, capacity, speed);

        database.add(newVehicle);
        return ResponseEntity.ok(newVehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() { return database; }


    // ! Flag: VEHICLE CREATION ENDPOINT
    /**
     * * Creates a vehicle based on query parameters.
     * * Returning the abstraction (Vehicle) allows polymorphic responses (Truck, Drone, Van).
     */
    @GetMapping("/create")
    public Vehicle createVehicle(
            @RequestParam String type,
            @RequestParam int capacity,
            @RequestParam int speed) {

        // * The controller does not know HOW to make a Truck; it asks the Factory.
        return vehicleFactory.createVehicle(type, capacity, speed);
    }
}