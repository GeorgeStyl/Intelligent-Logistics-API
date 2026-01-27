package org.stylianopoulos.logistics.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // * Static logger instance for the class
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleAllExceptions(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            // * Specifically logging the factory error for unknown vehicle types
            logger.warn("Input Validation Error: {}", ex.getMessage());
            return ResponseEntity.badRequest().build();
        } else if (ex instanceof NullPointerException) {
            // * Logging potential data missing or logic issues
            logger.error("Null Pointer Exception encountered: ", ex);
            return ResponseEntity.internalServerError().build();
        } else {
            // ? Catch-all for any other runtime or checked exceptions
            logger.error("Unexpected System Error: ", ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}