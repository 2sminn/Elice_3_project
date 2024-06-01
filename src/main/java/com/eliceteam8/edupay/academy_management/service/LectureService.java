package com.eliceteam8.edupay.academy_management.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.CreateLectureRequestDTO;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private AcademyRepository academyRepository;

    @Transactional
    public Lecture createLecture(CreateLectureRequestDTO lectureDTO) {
        // 주어진 academyId를 기반으로 학원 조회
        Academy academy = academyRepository.findById(lectureDTO.getAcademyId())
                .orElseThrow(() -> new RuntimeException("학원을 찾을 수 없습니다."));

        // Lecture 엔티티 생성 및 DTO 값 설정
        Lecture lecture = new Lecture();
        lecture.setLectureName(lectureDTO.getLectureName());
        lecture.setPrice(lectureDTO.getPrice());
        lecture.setAcademy(academy);  // 학원과 연관 설정

        // Lecture 엔티티를 데이터베이스에 저장
        return lectureRepository.save(lecture);
    }


    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Optional<Lecture> getLectureById(Long id) {
        return lectureRepository.findById(id);
    }

    @Transactional
    public Lecture updateLecture(Long id, CreateLectureRequestDTO lectureDTO) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("수정할 강의를 찾을 수 없습니다."));

        Academy academy = academyRepository.findById(lectureDTO.getAcademyId())
                .orElseThrow(() -> new RuntimeException("학원을 찾을 수 없습니다."));

        lecture.setLectureName(lectureDTO.getLectureName());
        lecture.setPrice(lectureDTO.getPrice());
        lecture.setAcademy(academy);

        return lectureRepository.save(lecture);
    }

    @Transactional
    public void deleteLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("삭제할 강의를 찾을 수 없습니다."));
        lectureRepository.delete(lecture);
    }
}






