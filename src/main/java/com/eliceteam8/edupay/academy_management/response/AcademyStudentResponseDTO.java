package com.eliceteam8.edupay.academy_management.response;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.AcademyDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AcademyStudentResponseDTO {
    private Long studentId;
    private String studentName;
    private String birthdate;
    private String phoneNumber;
    private String email;
    private String grade;
    private List<Lecture> Lectures;

}
