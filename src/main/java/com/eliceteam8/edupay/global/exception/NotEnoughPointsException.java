package com.eliceteam8.edupay.global.exception;

public class NotEnoughPointsException extends RuntimeException {
    public NotEnoughPointsException(String message) {
        super(message);
    }
}