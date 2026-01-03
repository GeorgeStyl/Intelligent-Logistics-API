package org.stylianopoulos.logistics.service;

import reactor.core.publisher.Mono;

// * Core Concept: The Functional Interface for the Strategy Pattern
@FunctionalInterface
public interface ShippingStrategy {
    Mono<Double> calculateCost(double weight);
}