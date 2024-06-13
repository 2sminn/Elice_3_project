package com.eliceteam8.edupay.academy_management.response;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.LectureSchedule;
import com.eliceteam8.edupay.academy_management.entity.LectureStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class LectureDTO {
    private Long lectureId;
    private String lectureName;
    private int price;
    private String teacherName;
    private LectureStatus lectureStatus;
    private List<LectureSchedule> lectureSchedules;
    private List<Long> studentId;
    // private Long AcademyId;
}
