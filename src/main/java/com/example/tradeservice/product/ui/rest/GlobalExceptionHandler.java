package com.example.tradeservice.product.ui.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<String> exceptionHandler(Exception e) {
        log.error("Unexpected error occurred: ", e);
        return new ResponseEntity<>(
                "Internal server error.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MultipartException.class)
    ResponseEntity<String> handleMultipartException(MultipartException e) {
        log.error("MultipartException occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
