package com.eliceteam8.edupay.global.exception;

import com.eliceteam8.edupay.global.enums.ErrorCode;
import com.eliceteam8.edupay.global.enums.ExceptionCode;

public class CustomJWTException extends RuntimeException{


    private final ExceptionCode exceptionCode;

    public CustomJWTException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;

    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
