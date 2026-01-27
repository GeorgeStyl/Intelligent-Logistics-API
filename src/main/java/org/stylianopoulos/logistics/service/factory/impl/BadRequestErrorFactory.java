package org.stylianopoulos.logistics.service.factory.impl;

import org.stylianopoulos.logistics.dto.ErrorResponse;
import org.stylianopoulos.logistics.service.factory.ErrorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
public class BadRequestErrorFactory implements ErrorFactory {

    @Override
    public ErrorResponse createResponse(Exception ex) {
        // * Standardizing the response format for 400 Bad Request
        return new ErrorResponse(
                ex.getMessage(),
                "INVALID_INPUT",
                LocalDateTime.now()
        );
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}