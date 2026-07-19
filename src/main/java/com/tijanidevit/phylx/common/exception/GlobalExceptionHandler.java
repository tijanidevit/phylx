package com.tijanidevit.phylx.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tijanidevit.phylx.common.response.ApiResponse;
import com.tijanidevit.phylx.common.response.ApiResponses;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessRuleException(
            BusinessRuleException ex
    ) {

        log.warn(ex.getMessage());

        return ApiResponses.error(
                ex.getStatus(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        return ApiResponses.error(
                HttpStatus.BAD_REQUEST,
                "Validation failed.",
                getValidationErrors(ex)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingRequestBody(
            HttpMessageNotReadableException ex
    ) {

        return ApiResponses.error(
                HttpStatus.BAD_REQUEST,
                "Request body is required."
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(
            BadCredentialsException ex
    ) {

        return ApiResponses.error(
                HttpStatus.UNAUTHORIZED,
                "Invalid email or password."
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(
            AuthenticationException ex
    ) {

        return ApiResponses.error(
                HttpStatus.UNAUTHORIZED,
                "Authentication failed."
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(
            AccessDeniedException ex
    ) {

        return ApiResponses.error(
                HttpStatus.FORBIDDEN,
                "You do not have permission to access this resource."
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception ex
    ) {

        log.error("Unhandled exception", ex);

        return ApiResponses.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong. Please try again later."
        );
    }

    private Map<String, String> getValidationErrors(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }

        return errors;
    }
}