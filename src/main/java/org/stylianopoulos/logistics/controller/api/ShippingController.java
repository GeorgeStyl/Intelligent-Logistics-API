package org.stylianopoulos.logistics.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stylianopoulos.logistics.dto.OrderRequestDTO;
import org.stylianopoulos.logistics.service.OrderAsyncService;
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
    public ResponseEntity<String> processOrder(@RequestBody OrderRequestDTO request) {
        // * Trigger Thread 2
        orderAsyncService.processOrderInBackground(request);

        // * Thread 1: Returns immediately to Postman
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("PENDING");
    }
}