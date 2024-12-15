package com.hk.learningspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException
            (ResourceNotFoundException resourceNotFound, WebRequest request) {
        ErrorDetails notFoundException = new ErrorDetails(
                LocalDateTime.now(),
                resourceNotFound.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(notFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> handleResourceAlreadyExistsException
            (ResourceAlreadyExistsException alreadyExists, WebRequest request) {
        ErrorDetails alreadyExistsException = new ErrorDetails(
                LocalDateTime.now(),
                alreadyExists.getMessage(),
                request.getDescription(false),
                HttpStatus.CONFLICT
        );

        return new ResponseEntity<>(alreadyExistsException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(Exception exception, WebRequest request) {
        ErrorDetails customException = new ErrorDetails(
                LocalDateTime.now(),
                "Global Exception:: " + exception.getMessage(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new ResponseEntity<>(customException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
