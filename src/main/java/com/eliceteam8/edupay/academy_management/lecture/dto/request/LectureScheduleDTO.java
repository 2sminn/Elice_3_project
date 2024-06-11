package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LectureScheduleDTO {

    @NotNull(message = "요일을 입력해주세요.")
    private String day; // 강의 요일

    @NotNull(message = "시작 시간을 입력해주세요.")
    private String startTime; // 강의 시작시간

    @NotNull(message = "종료 시간을 입력해주세요.")
    private String endTime; // 강의 종료시간

}
