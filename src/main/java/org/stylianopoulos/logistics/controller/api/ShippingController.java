package org.stylianopoulos.logistics.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<String>> process(@RequestBody Order orderInput) {
        // * Trigger Thread 2
        orderAsyncService.processOrderInBackground(orderInput);
        // * This response is sent back to Postman immediately
        return Mono.just(
                    ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("PENDING")
        );
    }
}