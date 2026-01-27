package org.stylianopoulos.logistics.model;

import jakarta.persistence.*;

// * Base entity mapping for Single Table Inheritance
@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ! 'type' is marked as insertable/updatable false because the Discriminator manages it
    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    private int capacity;
    private int speed;

    // * Mandatory No-Args Constructor for JPA
    protected Vehicle() {
    }

    public Vehicle(String type, String licensePlate, int capacity, int speed) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.speed = speed;
    }

    public abstract String getVehicleType();

    public abstract String getVehicleLicensePlate();

    public abstract int getCapacity();

    public abstract int getSpeed();

    // ? Internal accessors used by the Factory logic
    // ! Changed from protected to public to allow Service access
    public String getInternalType() {
        return type;
    }

    public String getInternalLicensePlate() {
        return licensePlate;
    }

    public int getInternalCapacity() {
        return capacity;
    }

    public int getInternalSpeed() {
        return speed;
    }
}