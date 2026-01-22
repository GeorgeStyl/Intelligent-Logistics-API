package org.stylianopoulos.logistics.domain.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table("Vehicles")
public record VehicleRecord(
        String type,
        String licensePlate,
        int capacity,
        int speed
) {

    public VehicleRecord {
        // * Guard Clauses for Fail-Fast validation
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("VehicleRecord type cannot be empty");
        }

        if (licensePlate == null || licensePlate.length() < 5) {
            throw new IllegalArgumentException("Invalid license plate");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("VehicleRecord capacity cannot be negative");
        }

        if (speed <= 0) {
            throw new IllegalArgumentException("VehicleRecord speed cannot be negative");
        }
    }
}