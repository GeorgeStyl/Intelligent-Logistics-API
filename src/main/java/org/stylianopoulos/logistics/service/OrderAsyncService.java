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
        // * Senior Approach: Ensure the pipeline stays as a Mono<Order> before conversion.
        return shippingContext.execute(orderInput.shippingType(), orderInput.weight())

                // * map: Double -> Order (Success)
                .map(cost -> createProcessedOrder(orderInput, cost))

                // * flatMap: Wraps the blocking repository call into the reactive flow.
                // * We use Mono.fromSupplier or fromCallable for blocking JPA saves.
                .flatMap(order -> Mono.fromCallable(() -> orderRepository.save(order)))

                // * toFuture: Converts Mono<Order> directly to CompletableFuture<Order>.
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