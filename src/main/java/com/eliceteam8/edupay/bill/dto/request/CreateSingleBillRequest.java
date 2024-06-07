package com.eliceteam8.edupay.bill.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateSingleBillRequest {
    private Long studentId; // 학생 ID로 조회
    private String message; // 안내 메시지
}