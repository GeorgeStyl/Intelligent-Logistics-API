package org.stylianopoulos.logistics.controller;

import org.stylianopoulos.logistics.model.abstraction.Vehicle;
import org.stylianopoulos.logistics.service.VehicleFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * * @RestController marks this class as a handler for REST requests.
 * * All return objects are automatically serialized to JSON.
 */
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleFactory vehicleFactory;

    public VehicleController(VehicleFactory vehicleFactory) {
        this.vehicleFactory = vehicleFactory;
    }

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