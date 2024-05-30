package com.eliceteam8.edupay.bill.dto.request;

import com.eliceteam8.edupay.academy_management.lecture.dto.request.LectureDetail;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateBillRequest {
    //private StudentInfo studentInfo;
    //private List<LectureDetail> lectureDetails;
    private LocalDateTime dueDate;
    private String message;
}