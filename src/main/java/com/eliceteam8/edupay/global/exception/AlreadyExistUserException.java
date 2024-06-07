package com.eliceteam8.edupay.global.exception;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class AlreadyExistUserException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public AlreadyExistUserException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;

    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
