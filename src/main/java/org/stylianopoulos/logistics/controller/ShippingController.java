package org.stylianopoulos.logistics.controller;

import org.stylianopoulos.logistics.dto.ShippingRequest; // * Import the new Record
import org.stylianopoulos.logistics.service.ShippingContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    private final ShippingContext shippingContext;

    public ShippingController(ShippingContext shippingContext) {
        this.shippingContext = shippingContext;
    }

    // * Event Driven: This returns a Publisher (Mono). Netty will subscribe to it asynchronously.
    @PostMapping("/process")
    public Mono<Double> process(@RequestBody ShippingRequest request) {
        // ! Validation: Check if the method name here matches the record field name exactly
        return shippingContext.execute(request.shippingType(), 1.0);
    }
}