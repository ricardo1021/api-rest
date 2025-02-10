package com.apirest.exercise.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {
    private LocalDateTime localDateTime;
    private int status;
    private String message;
    private List<String> errors;
}
