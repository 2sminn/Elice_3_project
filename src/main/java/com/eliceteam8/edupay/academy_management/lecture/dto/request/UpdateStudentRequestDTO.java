package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateStudentRequestDTO {

    @NotNull(message = "원생 이름을 입력해주세요.")
    private String studentName;

    @NotNull(message = "생일을 입력해주세요.")
    private String birthDate;

    @NotNull(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotNull(message = "학교 이름을 입력해주세요.")
    private String schoolName;

    @NotNull(message = "학년을 입력해주세요.")
    private String grade;

    @NotNull(message = "이메일을 입력해주세요.")
    private String email;

    @NotNull(message = "강의 ID를 입력해주세요.")
    private List<Long> lectureIds = new ArrayList<>();

}
