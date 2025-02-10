package com.apirest.exercise.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class ResponseEntityExceptionHandler {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> badRequestResponseExceptions(Exception ex) {
        List<String> errors = new ArrayList<>();

        if (ex instanceof MethodArgumentNotValidException validationException) {
            validationException.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        } else if (ex instanceof BadRequestException badRequestException) {
            errors.add(badRequestException.getMessage());
        } else if (ex instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            errors.add(methodArgumentTypeMismatchException.getMessage());
        } else {
            errors.add("An unexpected error occurred");
        }

        errors.forEach(error -> log.info(MessageFormat.format("Bad Request Exception: {0}", error)));

        return ResponseEntity.badRequest().body(new ResponseError(LocalDateTime.now(), BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), errors));
    }
}
