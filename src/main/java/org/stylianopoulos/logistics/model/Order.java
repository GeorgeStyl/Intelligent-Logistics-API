package org.stylianopoulos.logistics.model;

import jakarta.persistence.*;

// * Represents an Order record in the PostgreSQL 'orders' table
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ? Using quoted identifiers if you used camelCase in your schema.sql
    @Column(name = "\"customerName\"")
    private String customerName;

    private double weight;
    private String destination;

    // * Mandatory No-Args Constructor for JPA
    protected Order() {}

    public Order(String customerName, double weight, String destination) {
        this.customerName = customerName;
        this.weight = weight;
        this.destination = destination;
    }

    // ! Standard getters are required for the persistence layer to read data
    public Integer getId() { return id; }
    public String getCustomerName() { return customerName; }
    public double getWeight() { return weight; }
    public String getDestination() { return destination; }
}