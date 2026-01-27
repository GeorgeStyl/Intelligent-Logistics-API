package org.stylianopoulos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    // ! Matches SERIAL PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private String destination;

    @Column(name = "shippingtype", nullable = false)
    private String shippingType;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double cost;

    // ? JPA Requirement: No-args constructor
    public OrderEntity() {}

    // * Manual Getters and Setters for Data Access
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getShippingType() { return shippingType; }
    public void setShippingType(String shippingType) { this.shippingType = shippingType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}