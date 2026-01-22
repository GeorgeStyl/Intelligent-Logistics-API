package org.stylianopoulos.logistics.dto;

// * Senior Style: Immutable DTO for order input
public record OrderRequestDTO(
        String orderId,
        String customerName,
        double weight,
        String destination,
        String shippingType,
        String Status,
        Double Cost
) {}