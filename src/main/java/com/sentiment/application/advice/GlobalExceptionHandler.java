package com.sentiment.application.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleFileNotFoundException(FileNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "File not found: " + ex.getMessage());
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<?>> handleIOException(IOException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "IO EXCEPTION: " + ex.getMessage());
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, "RuntimeException EXCEPTION: " + ex.getMessage());
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(LocalDateTime.now(), null, apiError);
        return new ResponseEntity<>(apiResponse, apiError.getStatus());
    }
}

