package com.eliceteam8.edupay.global.exception;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class NotFoundRefreshTokenException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public NotFoundRefreshTokenException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;

    }

}
