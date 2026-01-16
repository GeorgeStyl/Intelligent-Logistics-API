package org.stylianopoulos.logistics.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stylianopoulos.logistics.service.strategy.ShippingStrategy;
import reactor.core.publisher.Mono;


@Service
public class ShippingContext {
    // ! Using a proper Logger instead of System.out
    private static final Logger log = LoggerFactory.getLogger(ShippingContext.class);
    private final Map<String, ShippingStrategy> strategies;

    public ShippingContext(List<ShippingStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toUnmodifiableMap( // ? Unmodifiable map so that the data don't change -> Thread Safety
                        ShippingStrategy::getStrategyName,
                        strategy -> strategy
                ));
    }

    public Mono<Double> execute(String type, double weight) {
        return Mono.justOrEmpty(type)
                .map(String::toUpperCase)
                .flatMap(key -> Mono.justOrEmpty(strategies.get(key)))
                // ? The Context does NOT calculate. It calls the strategy's method.
                .flatMap(strategy -> strategy.calculateCost(weight))
                // * Log Asynchronously
                .doOnNext(cost -> log.info("Calculated shipping cost for type {}: {}", type, cost))
                .switchIfEmpty(Mono.error(() -> new IllegalArgumentException("Unsupported shipping: " + type)));
    }
}

