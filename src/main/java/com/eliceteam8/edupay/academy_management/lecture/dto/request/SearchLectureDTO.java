package com.eliceteam8.edupay.academy_management.lecture.dto.request;

import com.eliceteam8.edupay.academy_management.entity.LectureStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchLectureDTO {

    private String lectureName;
    private String teacherName;
    private LectureStatus lectureStatus;
    private int page;
    private int size;
}
