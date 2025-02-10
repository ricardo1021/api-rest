package com.apirest.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BusinessException extends ResponseStatusException {
    public BusinessException(HttpStatusCode status) {
        super(status);
    }

    public BusinessException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }
}
