package org.stylianopoulos.logistics.service.strategy;

import org.stylianopoulos.logistics.model.Order;
import reactor.core.publisher.Mono;

public interface ShippingStrategy {
    String getStrategyName(); // Should return "EXPRESS", "STANDARD", etc.
    Double calculateCost(double weight);
    String getThreadNamePrefix(String threadName);
}