package com.eliceteam8.edupay.academy_management.response;

import com.eliceteam8.edupay.academy_management.lecture.dto.request.LectureDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AcademyStudentResponseDTO {
    private Long studentId;
    private String studentName;
    private String birthdate;
    private String phoneNumber;
    private String email;
    private String grade;

    private List<LectureDetail> lectureDetails;

    private Long Academy;
}
