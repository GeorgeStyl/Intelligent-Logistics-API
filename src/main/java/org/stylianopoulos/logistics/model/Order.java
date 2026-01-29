package org.stylianopoulos.logistics.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class) // This "listens" for the create event
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Matches SERIAL in schema

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private String destination;

    @Column(name = "shipping_type", nullable = false)
    private String shippingType;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double cost;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    public Order() {}

    public Order(String customerName, Double weight, String destination, String shippingType, String status, Double cost) {
        this.customerName = customerName;
        this.weight = weight;
        this.destination = destination;
        this.shippingType = shippingType;
        this.status = status;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getCost() { return cost; }
    public String getDestination() { return destination; }
    public String getShippingType() { return shippingType; }
}