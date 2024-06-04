package com.eliceteam8.edupay.global.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    //global
    INVALID_INPUT_VALUE(400, "G-001","올바른 입력값이 아닙니다."),
    ACCESS_DENIED(403,"G-002","접근 권한이 없습니다."),


    //token
    EXPIRED_TOKEN(401,"TOKEN-001","토큰이 만료되었습니다."),
    INVALID_TOKEN(401,"TOKEN-002","유효하지 않은 토큰입니다."),
    MALFORM_TOKEN(401,"TOKEN-003","토큰의 형식이 잘못되었습니다."),
    INVALID_SIGNATURE(401,"TOKEN-004","토큰의 서명이 유효하지 않습니다."),
    ERROR_TOKEN(401,"TOKEN-005","토큰이 잘못되었습니다."),
    //토큰생성 오류
    TOKEN_CREATION_ERROR(500,"TOKEN-006","토큰 생성 오류"),


    //user
    //이미 사용중인 이메일
    ALREADY_EXIST_EMAIL(400,"USER-001","이미 사용중인 이메일입니다."),
    NOT_FOUND_USER(404,"USER-002","사용자를 찾을 수 없습니다."),
    //로그인 실패
    LOGIN_FAILED(400,"USER-003","로그인에 실패하였습니다, 이메일 또는 비밀번호를 확인해주세요."),

    UNIQUE_VIOLATION(400, "USER-004", "이메일 또는 사업자번호가 중복된 값이 존재합니다.");


    private final int status;
    private final String code;
    private final String message;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
