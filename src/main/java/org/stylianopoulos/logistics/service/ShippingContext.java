package org.stylianopoulos.logistics.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class ShippingContext {
    private final Map<String, ShippingStrategy> strategies = new ConcurrentHashMap<>();

    public ShippingContext () {
        // ! To avoid if - else - switch -> use map
        strategies.put("STANDARD", weight -> Mono.just(weight * 5.0));
        strategies.put("EXPRESS", weight -> Mono.just(weight * 15.0));
        strategies.put("FREE", weight -> Mono.just(weight * 0.0));
    }

    public Mono<Double> execute(String type, double weight) {
        return strategies.getOrDefault(type, w -> Mono.error(new RuntimeException("invalid Type")))
                .calculateCost(weight);
    }
}
