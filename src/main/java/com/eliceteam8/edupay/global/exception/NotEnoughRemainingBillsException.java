package com.eliceteam8.edupay.global.exception;

public class NotEnoughRemainingBillsException extends RuntimeException {
    private String code;

    public NotEnoughRemainingBillsException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}