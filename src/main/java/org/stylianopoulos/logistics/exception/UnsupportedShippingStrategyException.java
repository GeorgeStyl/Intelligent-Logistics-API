package org.stylianopoulos.logistics.exception;

public class UnsupportedShippingStrategyException extends RuntimeException {
    public UnsupportedShippingStrategyException(String type) {
        super("Logistics Alert: The shipping method '" + type + "' is not a supported type.");
    }
}