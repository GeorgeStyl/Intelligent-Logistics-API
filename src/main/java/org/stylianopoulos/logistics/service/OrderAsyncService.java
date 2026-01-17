package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.domain.entity.Order;
import org.stylianopoulos.logistics.repository.OrderRepository;
import org.stylianopoulos.logistics.service.strategy.ShippingContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

// ! Async Service Orchestration
@Service
public class OrderAsyncService {

    private final ShippingContext shippingContext;
    private final OrderRepository orderRepository;

    public OrderAsyncService(ShippingContext shippingContext, OrderRepository orderRepository) {
        this.shippingContext = shippingContext;
        this.orderRepository = orderRepository;
    }

    // ! Non-Blocking to Async Bridge
    @Async("logisticsExecutor")
    public CompletableFuture<Order> processOrderInBackground(Order orderInput) {
        return Mono.delay(Duration.ofSeconds(3))
                // ! After 3 seconds, proceed to shipping context logic
                .flatMap(delay -> shippingContext.execute(
                            orderInput.shippingType(), orderInput.weight()
                ))
                // ? Map the result to our final Processed Order object
                .map(cost -> createProcessedOrder(orderInput, cost))
                // ! Bridge: Convert the reactive stream back to a Java Future for the @Async caller
                .toFuture();
    }

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