package org.stylianopoulos.logistics.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table ("orders")
public record Order(
        @Id Long id,
        String customerName,
        Double weight,
        String destination,
        String shippingType,
        String status,
        Double cost,
        LocalDateTime createdAt
) {
    public Order {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if (weight != null && weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }

        if (cost != null && cost < 0) {
            throw new IllegalArgumentException("Shipping cost cannot be negative");
        }

        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Destination is required");
        }

        // ! Status is defined automatically
    }
}