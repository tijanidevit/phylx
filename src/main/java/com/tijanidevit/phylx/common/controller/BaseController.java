package com.tijanidevit.phylx.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tijanidevit.phylx.common.response.ApiResponse;
import com.tijanidevit.phylx.common.response.ApiResponses;

public abstract class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> success(
            String message,
            T data
    ) {
        return ApiResponses.success(message, data);
    }

    protected ResponseEntity<ApiResponse<Void>> success(
            String message
    ) {
        return ApiResponses.success(message);
    }

    protected <T> ResponseEntity<ApiResponse<T>> created(
            String message,
            T data
    ) {
        return ApiResponses.created(message, data);
    }

    protected ResponseEntity<ApiResponse<Void>> created(
            String message
    ) {
        return ApiResponses.created(message);
    }

    protected ResponseEntity<ApiResponse<Void>> accepted(
            String message
    ) {
        return ApiResponses.accepted(message);
    }

    protected ResponseEntity<Void> noContent() {
        return ApiResponses.noContent();
    }

    protected ResponseEntity<ApiResponse<Void>> error(
            HttpStatus status,
            String message
    ) {
        return ApiResponses.error(status, message);
    }

    protected ResponseEntity<ApiResponse<Void>> error(
            String message
    ) {
        return ApiResponses.error(message);
    }

    protected ResponseEntity<ApiResponse<Void>> error(
            HttpStatus status,
            String message,
            Object errors
    ) {
        return ApiResponses.error(status, message, errors);
    }
}