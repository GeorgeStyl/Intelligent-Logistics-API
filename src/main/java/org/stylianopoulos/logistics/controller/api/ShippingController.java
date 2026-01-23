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

    // * Dependency Injection via Constructor (Senior approach over @Autowired)
    public ShippingController(OrderAsyncService orderAsyncService) {
        this.orderAsyncService = orderAsyncService;
    }

    // ? Why return Mono<ResponseEntity>?
    // ? Since you are using R2DBC and WebFlux, we wrap the response in a Mono.
    // ? This ensures the Netty thread remains non-blocking.
    @PostMapping
    public Mono<ResponseEntity<String>> processOrder(@RequestBody OrderRequestDTO request) {

        // * Triggering Thread 2: The background worker
        // ! This uses @Async + CompletableFuture internally (as per your requirements)
        orderAsyncService.processOrderInBackground(request);

        // * Thread 1: Returns a PENDING status immediately
        // ? This is effectively the 'Asynchronous Request-Reply' pattern.
        // ? We return 202 Accepted because the request is received but not yet finished.
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body("PENDING")
        );
    }
}