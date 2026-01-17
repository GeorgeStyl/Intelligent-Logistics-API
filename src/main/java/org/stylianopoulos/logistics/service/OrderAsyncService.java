package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.domain.entity.Order;
import org.stylianopoulos.logistics.repository.OrderRepository;
import org.stylianopoulos.logistics.service.strategy.ShippingContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.concurrent.CompletableFuture;

// ! Use Flag: Async Service Orchestration
@Service
public class OrderAsyncService {

    private final ShippingContext shippingContext;
    private final OrderRepository orderRepository;

    public OrderAsyncService(ShippingContext shippingContext, OrderRepository orderRepository) {
        this.shippingContext = shippingContext;
        this.orderRepository = orderRepository;
    }

    // ! Use Flag: Non-Blocking to Async Bridge
    @Async("logisticsExecutor")
    public CompletableFuture<Order> processOrderInBackground(Order orderInput) {
        return shippingContext.execute(orderInput.shippingType(), orderInput.weight())
                // ? Processed Order obj
                .map(cost -> createProcessedOrder(orderInput, cost))
                // ? Bridges the Project Reactor Mono back to Java's CompletableFuture.
                .toFuture();
    }

    // ! Use Flag: Domain Logic (Immutability)
    private Order createProcessedOrder(Order input, Double calculatedCost) {
        // * Using the Record constructor ensures thread-safety through immutability.
        return new Order(
                input.id(),
                input.customerName(),
                input.weight(),
                input.shippingType(),
                input.destination(),
                "PROCESSED",
                calculatedCost
        );
    }
}