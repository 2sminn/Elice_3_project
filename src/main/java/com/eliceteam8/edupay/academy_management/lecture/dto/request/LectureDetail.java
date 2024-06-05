package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureDetail {
    private Long lectureId;
    private Integer price;
    private String lectureName;
}
