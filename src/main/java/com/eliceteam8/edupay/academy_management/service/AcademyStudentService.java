package com.eliceteam8.edupay.academy_management.service;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.exception.ResourceNotFoundException;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademyStudentService {

    @Autowired
    private AcademyStudentRepository academyStudentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AcademyStudentResponseDTO> getAllAcademyStudents() {
        List<AcademyStudent> students = academyStudentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, AcademyStudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    public AcademyStudentResponseDTO getStudentById(Long studentId) {
        AcademyStudent student = academyStudentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return modelMapper.map(student, AcademyStudentResponseDTO.class);
    }

    @Transactional
    public AcademyStudent createStudent(AcademyStudent academyStudent) {
        return academyStudentRepository.save(academyStudent);
    }

    @Transactional
    public AcademyStudent updateStudent(Long studentId, AcademyStudent studentDetails) {
        AcademyStudent academyStudent = academyStudentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        academyStudent.setStudentName(studentDetails.getStudentName());
        academyStudent.setBirthDate(studentDetails.getBirthDate());
        academyStudent.setPhoneNumber(studentDetails.getPhoneNumber());
        academyStudent.setSchoolName(studentDetails.getSchoolName());
        academyStudent.setGrade(studentDetails.getGrade());
        academyStudent.setEmail(studentDetails.getEmail());

        return academyStudentRepository.save(academyStudent);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        AcademyStudent academyStudent = academyStudentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        academyStudentRepository.delete(academyStudent);
    }
}
