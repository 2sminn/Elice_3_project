package com.eliceteam8.edupay.academy_management.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.exception.ResourceNotFoundException;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.AcademyDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.CreateStudentRequestDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.SearchStudentDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.UpdateStudentRequestDTO;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.academy_management.repository.LectureRepository;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import com.eliceteam8.edupay.get_cost.repository.StudentPaymentStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademyStudentService {

    private final AcademyStudentRepository academyStudentRepository;
    private final AcademyRepository academyRepository;
    private final LectureService lectureService;

    @Autowired
    public AcademyStudentService(AcademyStudentRepository academyStudentRepository,
                                 LectureService lectureService,
                                 AcademyRepository academyRepository) {

        this.academyStudentRepository = academyStudentRepository;
        this.lectureService = lectureService;
        this.academyRepository = academyRepository;
    }


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

    public List<AcademyStudent> searchStudents(SearchStudentDTO criteria) {
        return academyStudentRepository.searchStudents(
                criteria.getStudentName(),
                criteria.getPhoneNumber(),
                criteria.getEmail()
        );

    }

    // , HttpSession session
    @Transactional
    public AcademyStudent createStudent(CreateStudentRequestDTO studentDetails) {

        //Academy academy = academyRepository.findById(studentDetails.getAcademy().getId())
                //.orElseThrow(() -> new ResourceNotFoundException("학원을 찾을 수 없습니다. ID: " + studentDetails.getAcademy().getId()));

        List<Lecture> lectures = new ArrayList<>();
        if (studentDetails.getLectureIds() != null && !studentDetails.getLectureIds().isEmpty()) {
            lectures = lectureService.getLecturesByIds(studentDetails.getLectureIds());
        }

        //List<Lecture> lectures = lectureService.getLecturesByIds(studentDetails.getLectureIds());

        AcademyStudent academyStudent = new AcademyStudent();
        academyStudent.setStudentName(studentDetails.getStudentName());
        academyStudent.setPhoneNumber(studentDetails.getPhoneNumber());
        academyStudent.setEmail(studentDetails.getEmail());
        academyStudent.setBirthDate(studentDetails.getBirthDate());
        academyStudent.setSchoolName(studentDetails.getSchoolName());
        academyStudent.setGrade(studentDetails.getGrade());
        academyStudent.setLectures(lectures);

        if (academyStudentRepository.existsByEmail(academyStudent.getPhoneNumber())) {
            throw new IllegalArgumentException("존재하는 휴대폰 번호입니다: " + academyStudent.getPhoneNumber());
        }

        if (academyStudentRepository.existsByPhoneNumber(academyStudent.getEmail())) {
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


        if (academyStudentRepository.existsByEmail(academyStudent.getEmail())) {
            throw new IllegalArgumentException("존재하는 휴대폰 번호입니다: " + academyStudent.getPhoneNumber());
        }

        if (academyStudentRepository.existsByPhoneNumber(academyStudent.getEmail())) {
            throw new IllegalArgumentException("존재하는 이메일 주소입니다: " + academyStudent.getEmail());
        }

        return academyStudentRepository.save(academyStudent);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        AcademyStudent academyStudent = academyStudentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 학생의 아이디를 찾을 수 없습니다: " + studentId));
        academyStudentRepository.delete(academyStudent);
    }

    @Autowired
    private StudentPaymentStatusRepository studentPaymentStatusRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Transactional
    public void deleteStudentWithDependencies(Long studentId) {
        // 연관된 bill 및 student_payment_status 삭제
        List<Bill> bills = billRepository.findByStudentId(studentId);
        for (Bill bill : bills) {
            studentPaymentStatusRepository.deleteByBillId(bill.getId());
            billRepository.delete(bill);
        }

        // 연관된 lecture 삭제
        List<Lecture> lectures = lectureRepository.findByAcademyStudentId(studentId);
        for (Lecture lecture : lectures) {
            lectureRepository.delete(lecture);
        }

        // 원생 삭제
        academyStudentRepository.deleteById(studentId);
    }


}
