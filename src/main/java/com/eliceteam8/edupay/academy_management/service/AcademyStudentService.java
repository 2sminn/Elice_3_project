package com.eliceteam8.edupay.academy_management.service;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.exception.ResourceNotFoundException;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import com.eliceteam8.edupay.global.exception.DuplicateStudentException;
import com.eliceteam8.edupay.global.exception.StudentNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 학생입니다: " + studentId));
        return modelMapper.map(student, AcademyStudentResponseDTO.class);
    }

    public List<AcademyStudent> findStudentsByName(String studentName) {
        List<AcademyStudent> students = academyStudentRepository.findByStudentName(studentName);
        if (students.isEmpty()) {
            throw new StudentNotFoundException("존재하지 않는 학생입니다: " + studentName);
        }
        return students;
    }

    @Transactional
    /*public AcademyStudent createStudent(AcademyStudent academyStudent) {

        if (academyStudentRepository.findByPhoneNumber(academyStudent.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("이미 존재하는 휴대폰 번호 입니다: " + academyStudent.getPhoneNumber());
        }

        if (academyStudentRepository.findByEmail(academyStudent.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일 주소 입니다: " + academyStudent.getEmail());
        }

        return academyStudentRepository.save(academyStudent);
    }*/

    public AcademyStudent createStudent(AcademyStudent academyStudent) {

        if (academyStudentRepository.findByEmail(academyStudent.getEmail()).isPresent()) {
            throw new DuplicateStudentException("존재하는 휴대폰 번호입니다: " + academyStudent.getPhoneNumber());
        }

        if (academyStudentRepository.findByEmail(academyStudent.getEmail()).isPresent()) {
            throw new DuplicateStudentException("존재하는 이메일 주소입니다: " + academyStudent.getEmail());
        }

        return academyStudentRepository.save(academyStudent);
    }

    @Transactional
    public AcademyStudent updateStudent(Long studentId, AcademyStudent studentDetails) {
        AcademyStudent academyStudent = academyStudentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 학생의 아이디를 찾을 수 없습니다: " + studentId));

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
                .orElseThrow(() -> new ResourceNotFoundException("해당 학생의 아이디를 찾을 수 없습니다: " + studentId));
        academyStudentRepository.delete(academyStudent);
    }
}
