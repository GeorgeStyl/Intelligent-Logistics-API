package org.stylianopoulos.logistics.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("EXPRESS")
public class ExpressShipping implements ShippingStrategy {

    @Override
    public Mono<Double> calculateCost(double weight) { return Mono.just(weight * 15.0); }

    @Override
    public String getStrategyName() {
        return "EXPRESS";
    }
}