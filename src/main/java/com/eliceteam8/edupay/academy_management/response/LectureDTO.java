package com.eliceteam8.edupay.academy_management.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureDTO {
    private Long lectureId;
    private String lectureName;
    private int price;
}
