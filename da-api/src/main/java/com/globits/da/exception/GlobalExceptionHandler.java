package com.globits.da.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.globits.da.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NotFoundException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .data(null)
                .errorCode("NOT_FOUND")
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .data(null)
                .errorCode("BAD_REQUEST")
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HandleIllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleHandleIllegalArgumentException(HandleIllegalArgumentException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .data(null)
                .errorCode(ex.getErrorValidation().getErrorCode())
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidFormatException(InvalidFormatException ex) {
        String errorDetail = "Invalid format for field: " + ex.getPathReference() + " with value: " + ex.getValue();

        ApiResponse<Object> response = ApiResponse.builder()
                .data(null)
                .errorCode("INVALID_FORMAT")
                .errorMessage(errorDetail)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
