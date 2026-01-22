package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.domain.entity.OrderRecord;
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
    public CompletableFuture<OrderRecord> processOrderInBackground(OrderRecord orderRecordInput) {
        return Mono.delay(Duration.ofSeconds(3))
                // ! After 3 seconds, proceed to shipping context logic
                .flatMap(delay -> shippingContext.execute(
                            orderRecordInput.shippingType(), orderRecordInput.weight()
                ))
                // ? Map the result to our final Processed OrderRecord object
                .map(cost -> createProcessedOrder(orderRecordInput, cost))
                .toFuture();
    }

    private OrderRecord createProcessedOrder(OrderRecord input, Double calculatedCost) {
        // * Using the Record constructor ensures thread-safety through immutability.
        return new OrderRecord(
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