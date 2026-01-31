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
import java.util.List;

@Service
public class OrderAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(OrderAsyncService.class);
    private final ShippingContext shippingContext;
    private final OrderRepository orderRepository;

    // ? List for randomizing status and escape if-else-switch
    private static final List<String> STATUS_POOL = List.of("PENDING", "SEND", "IN_PROGRESS", "SENDING");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public OrderAsyncService(ShippingContext shippingContext, OrderRepository orderRepository) {
        this.shippingContext = shippingContext;
        this.orderRepository = orderRepository;
    }

    @Async("logisticsExecutor")
    public void processOrderInBackground(OrderRequestDTO request) {
        String threadName = Thread.currentThread().getName();


        //********************************************************
        //* STEP 0) DISCARD WARM-UP REQUEST
        //*******************************************************/
        if ("WARMUP_BOT".equalsIgnoreCase(request.customerName())) {
            logger.info(
                    "\n\n!!!" +
                    "["+threadName+"]" +
                    "Ignoring internal warm-up request from {}",
                    request.customerName()
            );
            return;
        }

        //********************************************************
        //* STEP 1) SLEEP(3SEC)
        //* *******************************************************/
        String assignedStatus = STATUS_POOL.get(new java.util.Random().nextInt(STATUS_POOL.size()));
        try {


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

            //********************************************************
            //* STEP 2) CALCULATE COST && SAVE TO DB
            //*******************************************************/
            // ! Bridge: Convert Mono to Future to handle the result asynchronously
            shippingContext.execute(request.shippingType(), request.weight())
                    .toFuture()
                    .thenAccept(cost -> {
                        Order order = new Order(
                                request.customerName(),
                                request.weight(),
                                request.destination(),
                                request.shippingType(),
                                assignedStatus, // Flagged randomly without IF/ELSE
                                cost
                        );
                        // * 1. Save the order, JPA populates the 'id' field inside the 'order' object.
                        Order savedOrder = orderRepository.save(order);

                        // * 2. Get the ID that the DB generated
                        Long generatedId = Long.valueOf(savedOrder.getId());

                        logger.info("[SUCCESS] Order #{} saved for customer: {}. Total Cost: {}",
                                generatedId, savedOrder.getCustomerName(), cost);
                    })
                    .exceptionally(ex -> {
                        logger.error("[ERROR] Could not process order {}: {}", request.orderId(), ex.getMessage());
                        return null;
                    });

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}