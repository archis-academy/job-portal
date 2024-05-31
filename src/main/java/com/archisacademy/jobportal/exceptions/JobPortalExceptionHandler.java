package com.archisacademy.jobportal.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class JobPortalExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(JobPortalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<Object> handleJobException(final JobPortalServerException exception){
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),exception.getHttpStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(final Exception ex){
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleValidationException(final MethodArgumentNotValidException exception){
        LOGGER.error(exception.getMessage());
        Map<String,String> map = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            map.put(((FieldError) error).getField(),error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(map);
    }
}
