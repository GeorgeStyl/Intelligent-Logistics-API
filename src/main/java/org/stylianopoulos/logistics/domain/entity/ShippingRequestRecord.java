package org.stylianopoulos.logistics.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("shipping_requests")
public record ShippingRequestRecord(
        @Id Long id,
        String vehicle_type,
        Double payload_weight,
        int velocity
) {
    public ShippingRequestRecord {
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
