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


    @Builder
    public static ErrorResponse of(final ExceptionCode code) {
        return new ErrorResponse(code);
    }





}
