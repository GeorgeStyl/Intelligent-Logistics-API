package org.stylianopoulos.logistics.service.strategy;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.stylianopoulos.logistics.model.Order;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component("STANDARD")
public class StandardShipping implements ShippingStrategy {

    @Override
    public Double paymentProcessing(double weight) {
        return weight * 2.5;
    }

    @Override
    public String getThreadNamePrefix(String threadName) {
        return Thread.currentThread().getName();
    }

    @Override
    public String getStrategyName() {
        return "STANDARD";
    }
}