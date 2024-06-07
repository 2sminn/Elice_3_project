package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInfo {
    private Long studentId;
    private String studentName;
    private String grade;
    private String contact;
    private Long academyId;
}