package org.stylianopoulos.logistics.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("EXPRESS")
public class ExpressShipping implements ShippingStrategy {

    @Override
    public Double calculateCost(double weight) {
        return (weight * 2.5) + 1.5;
    }

    @Override
    public String getStrategyName() {
        return "EXPRESS";
    }
}