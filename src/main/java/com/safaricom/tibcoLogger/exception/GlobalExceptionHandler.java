package com.safaricom.tibcoLogger.exception;

import com.safaricom.tibcoLogger.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseQueryException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseQueryException(DatabaseQueryException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                false,
                "DATABASE_QUERY_ERROR",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                false,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred.",
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}