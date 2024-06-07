package com.eliceteam8.edupay.global.response;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String code;
    private String message;

//    @Builder.Default
//    private List<String> errors;

    private String messageDetail;

    //private LocalDateTime time;

    @Builder
    public ErrorResponse(final ExceptionCode code) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public ErrorResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
    public ErrorResponse(final ExceptionCode code, String messageDetail) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.messageDetail = messageDetail;
    }


    @Builder
    public static ErrorResponse of(final ExceptionCode code) {
        return new ErrorResponse(code);
    }


    @Builder
    public static ErrorResponse of(final ExceptionCode code,String messageDetail) {
        return new ErrorResponse(code,messageDetail);
    }



}
