package org.stylianopoulos.logistics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.stylianopoulos.logistics.dto.OrderRequestDTO;
import org.stylianopoulos.logistics.model.Order;
import org.stylianopoulos.logistics.repository.OrderRepository;
import org.stylianopoulos.logistics.service.strategy.ShippingContext;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(OrderAsyncService.class);
    private final ShippingContext shippingContext;
    private final OrderRepository orderRepository;

    private LocalTime now = LocalTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public OrderAsyncService(ShippingContext shippingContext, OrderRepository orderRepository) {
        this.shippingContext = shippingContext;
        this.orderRepository = orderRepository;
    }

    @Async("logisticsExecutor")
    public void processOrderInBackground(OrderRequestDTO request) {
        try {
            // ! Requirement: Blocking 3-second delay on the Worker Thread
            System.out.println("\n\n\nTime before sleeping for 3sec: " +
                    now.format(formatter));

            Thread.sleep(3000);

            System.out.println("\n\n\nTime after sleeping for 3sec: " +
                    now.format(formatter));

            // ! Bridge: Convert Mono to Future to handle the result asynchronously
            shippingContext.execute(request.shippingType(), request.weight())
                    .toFuture()
                    .thenAccept(cost -> {
                        Order order = new Order(
                                request.customerName(),
                                request.weight(),
                                request.destination(),
                                request.shippingType(),
                                "PROCESSED",
                                cost
                        );
                        orderRepository.save(order);
                        logger.info("[SUCCESS] Order {} saved. Final Cost: {}", request.orderId(), cost);
                    })
                    .exceptionally(ex -> {
                        logger.error("[FAILURE] Could not process order {}: {}", request.orderId(), ex.getMessage());
                        return null;
                    });

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}