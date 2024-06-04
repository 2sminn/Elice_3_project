package com.eliceteam8.edupay.academy_management.lecture.dto.request;

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

}

