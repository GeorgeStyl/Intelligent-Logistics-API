package org.stylianopoulos.logistics.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("FREE")
public class FreeShipping implements ShippingStrategy {
    @Override
    public Mono<Double> calculateCost(double weight) {
        return Mono.just(weight * 0); // ? Should be used under certain circumstances
    }

    @Override
    public String getStrategyName() {
        return "FREE";
    }
}
