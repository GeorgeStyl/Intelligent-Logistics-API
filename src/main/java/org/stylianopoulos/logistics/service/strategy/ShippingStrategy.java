package org.stylianopoulos.logistics.service.strategy;

import org.stylianopoulos.logistics.model.Order;
import reactor.core.publisher.Mono;

public interface ShippingStrategy {
    String getStrategyName(); // Should return "EXPRESS", "STANDARD", etc.
    Double paymentProcessing(double weight);
    String getThreadNamePrefix(String threadName);
}