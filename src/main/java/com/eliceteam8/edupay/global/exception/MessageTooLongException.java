package com.eliceteam8.edupay.global.exception;

import org.springframework.http.HttpStatus;

public class MessageTooLongException extends RuntimeException {
    private final HttpStatus status;
    private final String code;

    public MessageTooLongException(String message, String code) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}