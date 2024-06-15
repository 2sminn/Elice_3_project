package com.eliceteam8.edupay.bill.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BillInfoResponse {
    private String academyName;
    private String studentName;
    private String grade;
    private String contact;
    private List<String> lectureNames;
    private long totalPrice;
    private LocalDateTime dueDate;
    private String message;
    private Long billId; // Bill ID 추가
}
