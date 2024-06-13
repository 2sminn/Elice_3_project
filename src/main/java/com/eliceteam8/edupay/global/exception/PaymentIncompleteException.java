package com.eliceteam8.edupay.global.exception;

import org.springframework.http.HttpStatus;

public class PaymentIncompleteException extends RuntimeException {
    private final HttpStatus status;

    public PaymentIncompleteException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
