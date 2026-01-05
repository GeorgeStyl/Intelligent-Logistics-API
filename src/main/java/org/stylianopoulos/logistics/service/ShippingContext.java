package org.stylianopoulos.logistics.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.stylianopoulos.logistics.service.strategy.ShippingStrategy;
import reactor.core.publisher.Mono;


@Service
public class ShippingContext {
    private final Map<String, ShippingStrategy> strategies;

    public ShippingContext(List<ShippingStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toUnmodifiableMap( // ? Unmodifiable map so that the data don't change -> Thread Safety
                        ShippingStrategy::getStrategyName,
                        strategy -> strategy
                ));
    }

    public Mono<Double> execute(String type, double weight) {
        return Mono.justOrEmpty(strategies.get(type.toUpperCase()))
                .flatMap(strategy -> strategy.calculateCost(weight))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid Shipping Type: " + type)));
    }
}

