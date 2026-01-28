package org.stylianopoulos.logistics.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.dto.OrderRequestDTO;
import org.stylianopoulos.logistics.service.OrderAsyncService;
import reactor.core.publisher.Mono;

// ! Standard REST Controller for the Logistics API
@RestController
@RequestMapping("/orders")
public class ShippingController {

    private final OrderAsyncService orderAsyncService;


    // * Dependency Injection via Constructor
    public ShippingController(OrderAsyncService orderAsyncService) {
        this.orderAsyncService = orderAsyncService;
    }


    // ? This ensures the Netty thread remains non-blocking.
    @PostMapping
    public Mono<ResponseEntity<String>> processOrder(@RequestBody OrderRequestDTO request) {
        String threadName = Thread.currentThread().getName();

        System.out.println(
                "[" +threadName+ "-Thread] " +
                "Received order request: " +
                request
        );
        // * Triggering Thread 2: The background worker
        // ! This uses @Async + CompletableFuture internally (as per your requirements)
        orderAsyncService.processOrderInBackground(request);

        // * Thread 1: Returns a PENDING status immediately
        // ? This is effectively the 'Asynchronous Request-Reply' pattern.
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body("PENDING")
        );
    }
}