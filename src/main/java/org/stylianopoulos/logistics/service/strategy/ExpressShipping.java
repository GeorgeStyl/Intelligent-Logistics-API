package org.stylianopoulos.logistics.service.strategy;

import org.springframework.stereotype.Component;
import org.stylianopoulos.logistics.model.Order;
import reactor.core.publisher.Mono;

@Component("EXPRESS")
public class ExpressShipping implements ShippingStrategy {

    @Override
    public Double calculateCost(double weight) {
        double baseCost = weight * 2.5;
        return baseCost * 1.5;
    }
    @Override
    public String getThreadNamePrefix(String threadName) {
        return Thread.currentThread().getName();
    }

    @Override
    public String getStrategyName() {
        return "EXPRESS";
    }
}