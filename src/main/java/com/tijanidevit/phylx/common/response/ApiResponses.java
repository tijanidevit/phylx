package com.tijanidevit.phylx.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ApiResponses {

    private ApiResponses() {
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(
            String message,
            T data
    ) {

        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    public static ResponseEntity<ApiResponse<Void>> success(
            String message
    ) {

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message(message)
                        .build()
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(
            String message,
            T data
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<T>builder()
                                .success(true)
                                .message(message)
                                .data(data)
                                .build()
                );
    }

    public static ResponseEntity<ApiResponse<Void>> created(
            String message
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<Void>builder()
                                .success(true)
                                .message(message)
                                .build()
                );
    }

    public static ResponseEntity<ApiResponse<Void>> accepted(
            String message
    ) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        ApiResponse.<Void>builder()
                                .success(true)
                                .message(message)
                                .build()
                );
    }

    public static ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }

    public static ResponseEntity<ApiResponse<Void>> error(
            HttpStatus status,
            String message
    ) {

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.<Void>builder()
                                .success(false)
                                .message(message)
                                .build()
                );
    }

    public static ResponseEntity<ApiResponse<Void>> error(
            String message
    ) {

        return error(
                HttpStatus.BAD_REQUEST,
                message
        );
    }

    public static ResponseEntity<ApiResponse<Void>> error(
            HttpStatus status,
            String message,
            Object errors
    ) {

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.<Void>builder()
                                .success(false)
                                .message(message)
                                .errors(errors)
                                .build()
                );
    }
}