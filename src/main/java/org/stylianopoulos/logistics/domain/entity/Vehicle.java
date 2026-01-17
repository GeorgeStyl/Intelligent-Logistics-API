package org.stylianopoulos.logistics.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Vehicles")
public record Vehicle(
        @Id Long id,
        String type,
        String licensePlate
) {

    public Vehicle {
        // * Guard Clauses for Fail-Fast validation
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Vehicle type cannot be empty");
        }

        if (licensePlate == null || licensePlate.length() < 5) {
            throw new IllegalArgumentException("Invalid license plate");
        }
    }
}