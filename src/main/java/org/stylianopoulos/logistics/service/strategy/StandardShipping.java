package org.stylianopoulos.logistics.service.strategy;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component("STANDARD")
public class StandardShipping implements ShippingStrategy {

    @Override
    public Double calculateCost(double weight) {
        return weight * 2.5;
    }

    @Override
    public String getStrategyName() {
        return "STANDARD";
    }
}