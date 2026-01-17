package org.stylianopoulos.logistics.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
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
                .collect(Collectors.<ShippingStrategy, String, ShippingStrategy>toUnmodifiableMap(
                        strategy -> strategy.getStrategyName().toUpperCase(),
                        Function.identity()
                ));
    }

    /*
       The Map generates a hash-functions from both client's shipping type and
       from all 3 getShippingName() classes (from ShippingStrategy Interface)
       and by making the comparison between these 2, it finds what class must
       use
     */
    public Mono<Double> execute(String type, double weight) {
        return Mono.justOrEmpty(type)
                .map(String::toUpperCase)
                // ? Map to skip if - else
                .flatMap(key -> Mono.justOrEmpty(strategies.get(key)))
                .flatMap(strategy -> strategy.calculateCost(weight))
                .doOnNext(cost -> log.info("Calculated shipping cost for type {}: {}", type, cost))
                .switchIfEmpty(Mono.error(() -> new IllegalArgumentException("Unsupported shipping: " + type)));
    }
}


