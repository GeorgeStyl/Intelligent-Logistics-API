package org.stylianopoulos.logistics.controller;

import org.stylianopoulos.logistics.domain.entity.Order;
import org.stylianopoulos.logistics.service.OrderAsyncService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class ShippingController {

    private final OrderAsyncService orderAsyncService;

    public ShippingController(OrderAsyncService orderAsyncService) {
        this.orderAsyncService = orderAsyncService;
    }

    // ! Thread 1: Responds instantly to the client
    @PostMapping("/process")
    public Mono<String> process(@RequestBody Order orderInput) {
        // * Triggering Thread 2 (The Hand-off)
        orderAsyncService.processOrderInBackground(orderInput);

        // * This response is sent back to Postman immediately
        return Mono.just("PENDING");
    }
}