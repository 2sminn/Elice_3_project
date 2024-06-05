package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import com.eliceteam8.edupay.academy_management.entity.LectureStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLectureRequestDTO {
    @NotBlank
    private String lectureName;

    @NotNull
    private int price;

    @NotNull
    private Long academyId;

    @NotNull
    private String teacherName;

    @NotNull
    private LectureStatus lectureStatus;

}

