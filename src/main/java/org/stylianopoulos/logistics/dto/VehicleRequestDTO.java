package org.stylianopoulos.logistics.dto;


public record VehicleRequestDTO(
        String type,
        String licensePlate,
        int capacity,
        int speed
) {}