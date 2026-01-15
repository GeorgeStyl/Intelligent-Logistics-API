package org.stylianopoulos.logistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
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

    // ! Catching SQL grammar errors to prevent leaking raw trace to the client
    @ExceptionHandler(BadSqlGrammarException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleDatabaseError(BadSqlGrammarException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Database Mapping Error",
                        "message", "The database schema does not match the application model."
                )));
    }
}
