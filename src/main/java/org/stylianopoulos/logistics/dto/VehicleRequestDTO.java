package org.stylianopoulos.logistics.dto;

public record VehicleRequestDTO(int ID, String type, String licensePlate, int capacity, int speed) {}