package org.stylianopoulos.logistics.service.strategy;

import reactor.core.publisher.Mono;


public interface ShippingStrategy {
    Mono<Double> calculateCost(double weight);
    String getStrategyName();
}