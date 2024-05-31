package com.eliceteam8.edupay.global.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    //global
    INVALID_INPUT_VALUE(400, "G-001","올바른 입력값이 아닙니다."),


    //token
    EXPIRED_TOKEN(401,"TOKEN-001","토큰이 만료되었습니다."),
    INVALID_TOKEN(401,"TOKEN-002","유효하지 않은 토큰입니다."),
    MALFORM_TOKEN(401,"TOKEN-003","토큰의 형식이 잘못되었습니다."),
    INVALID_SIGNATURE(401,"TOKEN-004","토큰의 서명이 유효하지 않습니다."),
    ERROR_TOKEN(401,"TOKEN-005","토큰이 잘못되었습니다."),

    //db
    DUPLICATION_EMAIL(400,"DUPLICATION-001","이미 사용중인 이메일입니다."),


    //user
    //이미 사용중인 이메일
    ALREADY_EXIST_EMAIL(400,"USER-001","이미 사용중인 이메일입니다.");


    private final int status;
    private final String code;
    private final String message;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
