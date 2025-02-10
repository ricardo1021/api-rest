package com.apirest.exercise.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GenericExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessException(BusinessException businessException) {
        log.info(MessageFormat.format("Business Exception: {0}", businessException.getReason()));
        List<String> errors = new ArrayList<>();
        errors.add(businessException.getReason());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseError(LocalDateTime.now(),
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), errors));
    }
}
