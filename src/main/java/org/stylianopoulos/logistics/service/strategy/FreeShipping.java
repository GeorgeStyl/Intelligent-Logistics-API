package org.stylianopoulos.logistics.service.strategy;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component("FREE")
public class FreeShipping implements ShippingStrategy {

    @Override
    public Double calculateCost(double weight) {
        return 0.0;
    }

    @Override
    public String getThreadNamePrefix(String threadName) {
        return Thread.currentThread().getName();
    }

    @Override
    public String getStrategyName() {
        return "FREE";
    }
}
