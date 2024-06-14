package com.eliceteam8.edupay.global.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    //global
    INVALID_INPUT_VALUE(400, "G-001","올바른 입력값이 아닙니다."),
    ACCESS_DENIED(403,"G-002","접근 권한이 없습니다."),
    INVALID_REQUEST_VALUE(400,"G-003","요청값이 잘못되었습니다."),
    ILLEGAL_STATE(400,"G-004","상태값이 올바르지 않습니다."),



    //token
    EXPIRED_TOKEN(401,"TOKEN-001","토큰이 만료되었습니다."),
    INVALID_TOKEN(401,"TOKEN-002","유효하지 않은 토큰입니다."),
    MALFORM_TOKEN(401,"TOKEN-003","토큰의 형식이 잘못되었습니다."),
    INVALID_SIGNATURE(401,"TOKEN-004","토큰의 서명이 유효하지 않습니다."),
    ERROR_TOKEN(401,"TOKEN-005","토큰이 잘못되었습니다."),
    TOKEN_CREATION_ERROR(500,"TOKEN-006","토큰 생성 오류"),
    NOT_FOUND_TOKEN(401,"TOKEN-007","토큰을 찾을 수 없습니다."),


    //user
    //이미 사용중인 이메일
    ALREADY_EXIST_EMAIL(400,"USER-001","이미 사용중인 이메일입니다."),
    NOT_FOUND_USER(400,"USER-002","사용자를 찾을 수 없습니다."),
    LOGIN_FAILED(400,"USER-003","로그인에 실패하였습니다, 이메일 또는 비밀번호를 확인해주세요."),

    UNIQUE_VIOLATION(400, "USER-004", "이메일 또는 사업자번호가 중복된 값이 존재합니다."),
    BAD_PARAMETER(400, "USER-005", "파라미터 설정이 잘못되었습니다."),

    NOT_ACCEPTABLE_MEDIA_TYPE(406, "USER-005", "요청한 타입이 서버에서 지원되지 않습니다.");


    private final int status;
    private final String code;
    private final String message;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
