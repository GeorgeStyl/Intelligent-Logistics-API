package org.stylianopoulos.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stylianopoulos.logistics.model.Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    // * Returns all vehicles matching the discriminator type (Will be processed via Stream API)
    List<Vehicle> findByType(String type);
}