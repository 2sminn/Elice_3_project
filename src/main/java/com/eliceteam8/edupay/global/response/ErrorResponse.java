package com.eliceteam8.edupay.global.response;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private int status;
    private String code;
    private String message;
   // private List<FieldError> errors;    // 상세 에러 메시지
    private String reason;              // 에러 이유

    //private LocalDateTime time;

    @Builder
    public ErrorResponse(final ExceptionCode code) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    @Builder
    protected ErrorResponse(final ExceptionCode code, final String reason) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.reason = reason;
    }




    @Builder
    public static ErrorResponse of(final ExceptionCode code) {
        return new ErrorResponse(code);
    }

    @Builder
    public static ErrorResponse of(final ExceptionCode code, final String reason){
        return new ErrorResponse(code, reason);
    }



}
