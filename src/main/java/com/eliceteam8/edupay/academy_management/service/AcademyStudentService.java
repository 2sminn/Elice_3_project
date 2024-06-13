package com.eliceteam8.edupay.academy_management.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.exception.ResourceNotFoundException;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.CreateStudentRequestDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.SearchStudentDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.UpdateStudentRequestDTO;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.user.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademyStudentService {

    private final AcademyStudentRepository academyStudentRepository;
    private final AcademyRepository academyRepository;
    private final LectureService lectureService;
    private final AcademyService academyService;

    @Autowired
    public AcademyStudentService(AcademyStudentRepository academyStudentRepository,
                                 LectureService lectureService,
                                 AcademyRepository academyRepository,
                                 AcademyService academyService) {

        this.academyStudentRepository = academyStudentRepository;
        this.lectureService = lectureService;
        this.academyRepository = academyRepository;
        this.academyService = academyService;
    }


    @Autowired
    private ModelMapper modelMapper;

    /*public Page<AcademyStudentResponseDTO> getAllAcademyStudents(Pageable pageable) {
        Page<AcademyStudent> studentsPage = academyStudentRepository.findAll(pageable);
        //return studentsPage.map(student -> modelMapper.map(student, AcademyStudentResponseDTO.class));

        List<AcademyStudentResponseDTO> studentDTOs = studentsPage.stream()
                .map(student -> {
                    AcademyStudentResponseDTO dto = modelMapper.map(student, AcademyStudentResponseDTO.class);

                    // 청구서 상태별 건수 계산
                    long beforeCount = student.getBills().stream()
                            .filter(bill -> Status.BEFORE.equals(bill.getStatus()))
                            .count();
                    long paidCount = student.getBills().stream()
                            .filter(bill -> Status.PAID.equals(bill.getStatus()))
                            .count();

                    dto.setBeforePaymentCount((int) beforeCount);
                    dto.setPaidPaymentCount((int) paidCount);

                    return dto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(studentDTOs, pageable, studentsPage.getTotalElements());

    }*/

    public List<AcademyStudentResponseDTO> getAllAcademyStudents() {
        List<AcademyStudent> students = academyStudentRepository.findAll();

        // List<AcademyStudent>를 List<AcademyStudentResponseDTO>로 변환
        List<AcademyStudentResponseDTO> studentDTOs = students.stream()
                .map(student -> {
                    AcademyStudentResponseDTO dto = modelMapper.map(student, AcademyStudentResponseDTO.class);

                    // 청구서 상태별 건수 계산
                    long beforeCount = student.getBills().stream()
                            .filter(bill -> Status.BEFORE.equals(bill.getStatus()))
                            .count();
                    long paidCount = student.getBills().stream()
                            .filter(bill -> Status.PAID.equals(bill.getStatus()))
                            .count();

                    // DTO에 상태별 건수 설정
                    dto.setBeforePaymentCount((int) beforeCount);
                    dto.setPaidPaymentCount((int) paidCount);

                    return dto;
                })
                .collect(Collectors.toList());

        return studentDTOs;
    }

    /*public AcademyStudentResponseDTO getStudentById(Long studentId) {
        Optional<AcademyStudent> studentOptional = academyStudentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            AcademyStudent student = studentOptional.get();
            return modelMapper.map(student, AcademyStudentResponseDTO.class);
        } else {
            throw new ResourceNotFoundException("해당 학생을 찾을 수 없습니다: " + studentId);
        }


    }*/

    public AcademyStudentResponseDTO getStudentById(Long studentId) {
        Optional<AcademyStudent> studentOptional = academyStudentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            AcademyStudent student = studentOptional.get();

            // 청구서 상태별 건수 계산
            long beforeCount = student.getBills().stream()
                    .filter(bill -> Status.BEFORE.equals(bill.getStatus()))
                    .count();
            long paidCount = student.getBills().stream()
                    .filter(bill -> Status.PAID.equals(bill.getStatus()))
                    .count();

            // DTO로 매핑
            AcademyStudentResponseDTO dto = modelMapper.map(student, AcademyStudentResponseDTO.class);
            dto.setBeforePaymentCount((int) beforeCount);
            dto.setPaidPaymentCount((int) paidCount);

            return dto;
        } else {
            throw new ResourceNotFoundException("해당 학생을 찾을 수 없습니다: " + studentId);
        }
    }



    public List<AcademyStudentResponseDTO> searchStudents(SearchStudentDTO criteria) {
        List<AcademyStudent> students = academyStudentRepository.searchStudents(
                criteria.getStudentName(),
                criteria.getPhoneNumber(),
                criteria.getEmail(),
                criteria.getSchoolName()
        );
        return students.stream()
                .map(student -> {
                    AcademyStudentResponseDTO dto = modelMapper.map(student, AcademyStudentResponseDTO.class);

                    // 청구서 상태별 건수 계산
                    long beforeCount = student.getBills().stream()
                            .filter(bill -> Status.BEFORE.equals(bill.getStatus()))
                            .count();
                    long paidCount = student.getBills().stream()
                            .filter(bill -> Status.PAID.equals(bill.getStatus()))
                            .count();

                    // DTO에 건수 설정
                    dto.setBeforePaymentCount((int) beforeCount);
                    dto.setPaidPaymentCount((int) paidCount);

                    return dto;
                })
                .collect(Collectors.toList());
    }

        /*Page<AcademyStudent> searchStudentsPage = academyStudentRepository.searchStudents(
                criteria.getStudentName(),
                criteria.getPhoneNumber(),
                criteria.getEmail(),
                criteria.getSchoolName(),
                pageable
        );

        return searchStudentsPage.map(student -> {
            AcademyStudentResponseDTO dto = modelMapper.map(student, AcademyStudentResponseDTO.class);

            // Null 체크 추가
            if (student.getBills() != null) {
                long beforeCount = student.getBills().stream()
                        .filter(bill -> Status.BEFORE.equals(bill.getStatus()))
                        .count();
                long paidCount = student.getBills().stream()
                        .filter(bill -> Status.PAID.equals(bill.getStatus()))
                        .count();

                // DTO에 건수 설정
                dto.setBeforePaymentCount((int) beforeCount);
                dto.setPaidPaymentCount((int) paidCount);
            } else {
                dto.setBeforePaymentCount(0);
                dto.setPaidPaymentCount(0);
            }

            return dto;
        });*/



    @Transactional
    public AcademyStudent createStudent(CreateStudentRequestDTO studentDetails) {

        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long academyId = user.getAcademyId();
        Academy academy = academyService.findById(academyId);


        AcademyStudent academyStudent = new AcademyStudent();
        academyStudent.setStudentName(studentDetails.getStudentName());
        academyStudent.setPhoneNumber(studentDetails.getPhoneNumber());
        academyStudent.setEmail(studentDetails.getEmail());
        academyStudent.setBirthDate(studentDetails.getBirthDate());
        academyStudent.setSchoolName(studentDetails.getSchoolName());
        academyStudent.setGrade(studentDetails.getGrade());

        List<Lecture> lectures = new ArrayList<>();
        if (studentDetails.getLectureIds() != null && !studentDetails.getLectureIds().isEmpty()) {
            lectures = lectureService.getLecturesByIds(studentDetails.getLectureIds());
        }
        academyStudent.setLectures(lectures);

        academyStudent.setAcademy(academy);

        if (academyStudentRepository.existsByPhoneNumber(academyStudent.getPhoneNumber())) {
            throw new IllegalArgumentException("존재하는 휴대폰 번호입니다: " + academyStudent.getPhoneNumber());
        }

        if (academyStudentRepository.existsByEmail(academyStudent.getEmail())) {
            throw new IllegalArgumentException("존재하는 이메일 주소입니다: " + academyStudent.getEmail());
        }

        return academyStudentRepository.save(academyStudent);
    }

    @Transactional
    public AcademyStudent updateStudent(Long studentId, UpdateStudentRequestDTO studentDetails) {
        AcademyStudent academyStudent = academyStudentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 학생의 아이디를 찾을 수 없습니다: " + studentId));


        List<Lecture> lectures = lectureService.getLecturesByIds(studentDetails.getLectureIds());
        
        academyStudent.setStudentName(studentDetails.getStudentName());
        academyStudent.setBirthDate(studentDetails.getBirthDate());
        academyStudent.setPhoneNumber(studentDetails.getPhoneNumber());
        academyStudent.setSchoolName(studentDetails.getSchoolName());
        academyStudent.setGrade(studentDetails.getGrade());
        academyStudent.setEmail(studentDetails.getEmail());
        academyStudent.setLectures(lectures);


        /*if (academyStudentRepository.existsByEmail(academyStudent.getEmail())) {
            throw new IllegalArgumentException("존재하는 휴대폰 번호입니다: " + academyStudent.getPhoneNumber());
        }

        if (academyStudentRepository.existsByPhoneNumber(academyStudent.getEmail())) {
            throw new IllegalArgumentException("존재하는 이메일 주소입니다: " + academyStudent.getEmail());
        }*/

        return academyStudentRepository.save(academyStudent);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        //AcademyStudent academyStudent = academyStudentRepository.findById(studentId)
        //        .orElseThrow(() -> new ResourceNotFoundException("해당 학생의 아이디를 찾을 수 없습니다: " + studentId));
        //academyStudentRepository.delete(academyStudent);
        Optional<AcademyStudent> studentOptional = academyStudentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            AcademyStudent student = studentOptional.get();
            student.setDeleted(true); // 소프트 딜리트
            academyStudentRepository.save(student);
        } else {
            throw new RuntimeException("해당 학생의 아이디를 찾을 수 없습니다: " + studentId);
        }
    }


}
