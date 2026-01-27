package org.stylianopoulos.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stylianopoulos.logistics.model.Vehicle;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    // * Fetches the vehicle data from DB before the Factory creates the specific instance
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}