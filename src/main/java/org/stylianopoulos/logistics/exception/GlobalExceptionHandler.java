package org.stylianopoulos.logistics.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import java.util.Map;


// ! @RestControllerAdvice makes this class a global "catcher" for all controllers
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleRecordValidation(IllegalArgumentException ex) {
        // ! We return a Mono to maintain the Non-Blocking pipeline
        return Mono.just(ResponseEntity.badRequest().body(Map.of(
                "status", "400",
                "error", "Domain Validation Failed",
                "message", ex.getMessage()
        )));
    }
}
