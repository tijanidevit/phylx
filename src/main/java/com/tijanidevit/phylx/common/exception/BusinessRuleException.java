package com.tijanidevit.phylx.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BusinessRuleException extends RuntimeException {
    private final HttpStatus status;

    public BusinessRuleException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }

    public BusinessRuleException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
