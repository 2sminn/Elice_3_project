package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequestDTO {

    @NotBlank
    private String studentName;

    @NotBlank
    private String birthDate;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String schoolName;

    @NotBlank
    private String grade;
}
