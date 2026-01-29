package org.stylianopoulos.logistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VehicleResponseDTO(
        String type,

        @JsonProperty("license_plate") // Keeps JSON naming consistent with your DB/Schema
        String licensePlate,

        int speed,
        String status
) {}