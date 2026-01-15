package org.stylianopoulos.logistics.service;

import org.stylianopoulos.logistics.domain.entity.Order;
import org.stylianopoulos.logistics.repository.OrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OrderAsyncService {

    private final ShippingContext shippingContext;
    private final OrderRepository orderRepository;

    public OrderAsyncService(ShippingContext shippingContext, OrderRepository orderRepository) {
        this.shippingContext = shippingContext;
        this.orderRepository = orderRepository;
    }

    // ! Thread 2
    @Async("logisticsExecutor")
    public void processOrderInBackground(Order orderInput) {
        // ? The business logic and DB save happen on 'LogisticsWorker-n'
        shippingContext.execute(orderInput.shippingType(), orderInput.weight())
                .flatMap(cost -> {
                    Order finalized = new Order(
                            null,
                            orderInput.customerName(),
                            orderInput.weight(),
                            orderInput.destination(),
                            orderInput.shippingType(),
                            "COMPLETED",
                            cost,
                            LocalDateTime.now()
                    );
                    return orderRepository.save(finalized);
                })
                // * Since it's background, must subscribe to trigger the reactive chain
                .subscribe(
                        success -> System.out.println("Background Save Successful: ID " + success.id()),
                        error -> System.err.println("Background Error: " + error.getMessage())
                );
    }
}