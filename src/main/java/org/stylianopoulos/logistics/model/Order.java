package org.stylianopoulos.logistics.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String customer_name;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String shipping_type;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double cost;

    public Order() {} // Mandatory JPA constructor

    // Constructor used by OrderAsyncService
    public Order(String customerName, Double weight, String destination, String shippingType, String status, Double cost) {
        this.customer_name = customerName;
        this.weight = weight;
        this.destination = destination;
        this.shipping_type = shippingType;
        this.status = status;
        this.cost = cost;
    }
}