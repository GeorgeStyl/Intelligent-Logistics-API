package org.stylianopoulos.logistics.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.dto.VehicleResponseDTO;
import org.stylianopoulos.logistics.model.Vehicle;
import org.stylianopoulos.logistics.service.VehicleManagementService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleManagementService vehicleService;

    private static final Set<String> VEHICLES = Set.of("TRUCK", "VAN", "DRONE");

    public VehicleController(VehicleManagementService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/init")
    public ResponseEntity<List<VehicleResponseDTO>> createVehicle(@RequestParam String type) {
        List<Vehicle> vehicles = vehicleService.processVehicle(type);

        //**********************************************
        // * CONVERT TO DTO FOR JSON RESPONSE
        //*********************************************
        List<VehicleResponseDTO> response = vehicles.stream()
                .map(v -> new VehicleResponseDTO(
                        v.getVehicleType(),
                        v.getVehicleLicensePlate(),
                        v.getSpeed(),
                        "SUCCESS"
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}