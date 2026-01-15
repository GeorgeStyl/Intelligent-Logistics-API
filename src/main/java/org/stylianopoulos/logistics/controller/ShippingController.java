package org.stylianopoulos.logistics.controller;

import org.stylianopoulos.logistics.domain.entity.Order;
import org.stylianopoulos.logistics.repository.OrderRepository;
import org.stylianopoulos.logistics.service.ShippingContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
public class ShippingController {

    private final ShippingContext shippingContext;
    private final OrderRepository orderRepository;

    public ShippingController(ShippingContext shippingContext, OrderRepository orderRepository) {
        this.shippingContext = shippingContext;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/process")
    public Mono<Order> process(@RequestBody Order orderInput) {
        return shippingContext.execute(orderInput.shippingType(), orderInput.weight())
                .flatMap(calculatedCost -> {
                    // ? Creating the immutable record for persistence
                    Order finalizedOrder = new Order(
                            null,
                            orderInput.customerName(),
                            orderInput.weight(),
                            orderInput.destination(),
                            orderInput.shippingType(),
                            "PENDING",
                            calculatedCost,
                            LocalDateTime.now()
                    );
                    return orderRepository.save(finalizedOrder);
                })
                .log();
    }
}