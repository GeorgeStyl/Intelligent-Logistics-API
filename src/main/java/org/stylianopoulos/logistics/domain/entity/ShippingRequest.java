package org.stylianopoulos.logistics.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("shipping_requests")
public record ShippingRequest(
        @Id Long id,
        String vehicle_type,
        Double payload_weight,
        int velocity,
        Timestamp created_at
) {
    public ShippingRequest {
        if (payload_weight == null) {
            throw new IllegalArgumentException("payload_weight cannot be null");
        }
        if (velocity < 0) {
            throw new IllegalArgumentException("velocity cannot be negative");
        }
        if (vehicle_type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
    }
}
