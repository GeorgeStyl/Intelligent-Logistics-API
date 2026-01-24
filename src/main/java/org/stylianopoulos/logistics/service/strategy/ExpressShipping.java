package org.stylianopoulos.logistics.service.strategy;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component("EXPRESS")
public class ExpressShipping implements ShippingStrategy {

    @Override
    // * This allows the calling thread to remain free.
    public Mono<Double> calculateCost(double weight) {
        return Mono.fromFuture(calculateAsync(weight));
    }

    @Async
    public CompletableFuture<Double> calculateAsync(double weight) {
        // * Simulate complex calculation
        double result = (weight * 2.5) + 1.5;
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public String getStrategyName() {
        return "EXPRESS";
    }
}