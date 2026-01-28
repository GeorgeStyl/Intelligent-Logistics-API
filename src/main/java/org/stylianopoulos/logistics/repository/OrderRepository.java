package org.stylianopoulos.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stylianopoulos.logistics.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}