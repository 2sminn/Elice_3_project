package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchStudentDTO {
    private String studentName;
    private String phoneNumber;
    private String email;
    private String schoolName;
}
