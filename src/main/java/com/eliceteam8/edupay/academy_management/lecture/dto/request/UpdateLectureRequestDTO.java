package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import com.eliceteam8.edupay.academy_management.entity.LectureStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateLectureRequestDTO {

    @NotNull(message = "강의명을 입력해주세요.")
    private String lectureName;

    @NotNull(message = "수강료를 입력해주세요.")
    @Positive(message = "수강료에 마이너스는 입력할 수 없습니다.")
    private int price;

    @NotNull(message = "선생님 이름을 입력해주세요.")
    private String teacherName;

    @NotNull(message = "강의 상태를 선택해주세요.")
    private LectureStatus lectureStatus;

    @NotNull(message = "시간표를 입력해주세요.")
    private List<LectureScheduleDTO> lectureSchedules;
}
