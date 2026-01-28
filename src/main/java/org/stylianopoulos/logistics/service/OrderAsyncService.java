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


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public OrderAsyncService(ShippingContext shippingContext, OrderRepository orderRepository) {
        this.shippingContext = shippingContext;
        this.orderRepository = orderRepository;
    }

    @Async("logisticsExecutor")
    public void processOrderInBackground(OrderRequestDTO request) {
        String threadName = Thread.currentThread().getName();

        /********************************************************
        * STEP 0) DISCARD WARM-UP REQUEST
        *******************************************************/
        if ("WARMUP_BOT".equalsIgnoreCase(request.customerName())) {
            logger.info(
                    "\n\n!!!" +
                    "["+threadName+"]" +
                    "Ignoring internal warm-up request from {}",
                    request.customerName()
            );
            return;
        }

        /********************************************************
        * STEP 1) CALCULATE COST
        *******************************************************/
        try {
            // ! Bridge: Convert Mono to Future to handle the result asynchronously
            shippingContext.execute(request.shippingType(), request.weight())
                    .toFuture()
                    .thenAccept(cost -> {
                        Order order = new Order(
                                request.customerName(),
                                request.weight(),
                                request.destination(),
                                request.shippingType(),
                                "PENDING",
                                cost
                        );
                        orderRepository.save(order);
                        logger.info("[SUCCESS] Order {} saved. Final Cost: {}", request.orderId(), cost);
                    })
                    .exceptionally(ex -> {
                        logger.error("[FAILURE] Could not process order {}: {}", request.orderId(), ex.getMessage());
                        return null;
                    });

            /********************************************************
            * STEP 2) SLEEP(3SEC)
            * *******************************************************/
            // ! Requirement: Blocking 3-second delay on the Worker Thread
            System.out.println(
                    "\n\n!!!" +
                    "[" + threadName + "]" +
                    " Time before sleeping for 3sec: " +
                    LocalTime.now().format(formatter) +
                    "!!!"
            );

            Thread.sleep(3000);

            System.out.println(
                    "\n\n!!!" +
                    "[" + threadName + "]" +
                    "Time after sleeping for 3sec: " +
                    LocalTime.now().format(formatter) +
                    "!!!\n\n"
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}