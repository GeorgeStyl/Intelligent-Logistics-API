package org.stylianopoulos.logistics.service.factory;

import org.stylianopoulos.logistics.dto.ErrorResponse;
import org.springframework.http.HttpStatus;

public interface ErrorFactory {
    ErrorResponse createResponse(Exception ex);
    HttpStatus getStatus();
}