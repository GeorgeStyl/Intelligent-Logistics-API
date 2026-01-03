package org.stylianopoulos.logistics.service.strategy;

import org.stylianopoulos.logistics.service.ShippingStrategy;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("STANDARD")
public class StandardShipping implements ShippingStrategy {
    @Override
    public Mono<Double> calculateCost(double weight) {
        return Mono.just(weight * 5.0);
    }
}