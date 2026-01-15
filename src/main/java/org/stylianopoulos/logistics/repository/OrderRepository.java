package org.stylianopoulos.logistics.repository;

import org.stylianopoulos.logistics.domain.entity.Order;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;


// * The repository handles Mono/Flux return types automatically
@Repository
public interface OrderRepository extends R2dbcRepository<Order, Long> {
}