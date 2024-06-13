package com.eliceteam8.edupay.global.exception;

public class NotEnoughPointsException extends RuntimeException {
    private final String code;

    public NotEnoughPointsException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}