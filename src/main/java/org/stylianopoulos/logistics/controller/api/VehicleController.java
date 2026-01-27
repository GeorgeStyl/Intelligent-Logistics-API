package org.stylianopoulos.logistics.controller.api;

import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.service.VehicleManagementService;

@RestController
@RequestMapping("/vehicles/init")
public class VehicleController {

    private final VehicleManagementService vehicleService;

    public VehicleController(VehicleManagementService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/{type}/{licensePlate}")
    public void createVehicle(@PathVariable String type, @PathVariable String licensePlate) {
        //  Return 200 and Log logistics
        vehicleService.processVehicle(type, licensePlate);
    }
}