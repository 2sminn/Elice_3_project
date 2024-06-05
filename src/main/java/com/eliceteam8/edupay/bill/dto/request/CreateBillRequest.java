package com.eliceteam8.edupay.bill.dto.request;

import com.eliceteam8.edupay.academy_management.lecture.dto.request.LectureDetail;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.StudentInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreateBillRequest {
    private String studentName; // 학생 이름으로 조회
    private LocalDateTime dueDate; // 결제 기한
    private String message; // 안내 메시지
}