package org.stylianopoulos.logistics.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stylianopoulos.logistics.model.Order;
import org.stylianopoulos.logistics.repository.OrderRepository;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final OrderRepository orderRepository;



    // * Corrected Constructor Name and added OrderRepository
    public AnalyticsController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> getFullAnalytics() {
        Map<String, Object> dashboard = new HashMap<>();

        // 1)
        dashboard.put("total_income: ", calculateTotalIncome());

        // 2)
        dashboard.put("orders_by_shipping: ", groupByDestination());

        // 3)
        dashboard.put("top_order: ", getMaxOrder());

        // 4)
        dashboard.put("delayed_ids: ", getDelayedId());

        return ResponseEntity.ok(dashboard);
    }



    //******************************************************
    // ! STREAM APIs
    //******************************************************

    //**********************************************
    // * 1) reduce
    //*********************************************
    private Double calculateTotalIncome() {
        return orderRepository
                .findAll()
                .stream()
                .map(Order::getCost)
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);
    }


    //**********************************************
    // * 2) GROUPBY(DESTINATION)
    // *********************************************
    private Map<String, List<Order>> groupByDestination() {
        return orderRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(Order::getDestination));
    }


    //**********************************************
    // * 3) MAX(Order)
    // *********************************************
    private Order getMaxOrder() {
        return orderRepository.findAll()
                .stream()
                .max(Comparator.comparing(Order::getCost))
                .orElseThrow(() -> new RuntimeException("No orders found in the database"));
    }

    //**********************************************
    // * 4) FIND DELAYED IDs (filter map)
    // *********************************************
    private List<Integer> getDelayedId() {
        return orderRepository.findAll()
                .stream()
                .filter(order -> "DELAYED".equalsIgnoreCase(order.getStatus()))
                .map(Order::getId)
                .toList();
    }
}