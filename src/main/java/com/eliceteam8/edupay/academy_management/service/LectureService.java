package com.eliceteam8.edupay.academy_management.service;

import com.eliceteam8.edupay.academy_management.entity.*;
import com.eliceteam8.edupay.academy_management.exception.ResourceNotFoundException;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.*;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.repository.LectureRepository;
import com.eliceteam8.edupay.academy_management.repository.LectureScheduleRepository;
import com.eliceteam8.edupay.academy_management.response.LectureDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectureService {

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Autowired
    private LectureScheduleRepository lectureScheduleRepository;

    public List<Lecture> getLecturesByStudentId(Long studentId) {
        return lectureRepository.findByAcademyStudentId(studentId);
    }

    public Lecture saveLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }


    //학생이 수강중인 강의 조회
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private AcademyRepository academyRepository;

    @Transactional
    public Lecture createLecture(CreateLectureRequestDTO lectureDTO) {

        //Academy academy = academyRepository.findById(lectureDTO.getAcademyId())
        //  .orElseThrow(() -> new RuntimeException("학원을 찾을 수 없습니다."));

        // Lecture 엔티티 생성 및 DTO 값 설정
        Lecture lecture = new Lecture();
        lecture.setLectureName(lectureDTO.getLectureName());
        lecture.setPrice(lectureDTO.getPrice());
        lecture.setTeacherName(lectureDTO.getTeacherName()); //과목 담당 선생님 이름 입력
        lecture.setLectureStatus(LectureStatus.valueOf(lectureDTO.getLectureStatus())); // 과목 수강 상태 입력

        List<LectureSchedule> schedules = lectureDTO.getLectureSchedules().stream()
                .map(dto -> {
                    LectureSchedule schedule = new LectureSchedule();
                    schedule.setDay(dto.getDay());
                    schedule.setStartTime(dto.getStartTime());
                    schedule.setEndTime(dto.getEndTime());
                    schedule.setLecture(lecture);
                    return schedule;
                })
                .collect(Collectors.toList());
        lecture.setLectureSchedules(schedules);


        lectureRepository.save(lecture);
        lectureScheduleRepository.saveAll(schedules);

        return lecture;
    }


    @Autowired
    private ModelMapper modelMapper;

    /*public Page<LectureDTO> getAllLectures(Pageable pageable) {
        Page<Lecture> lecturesPage = lectureRepository.findAll(pageable);
        return lecturesPage.map(lecture -> modelMapper.map(lecture, LectureDTO.class));
    }*/

    public List<LectureDTO> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAll();
        return lectures.stream()
                .map(lecture -> modelMapper.map(lecture, LectureDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<Lecture> getLectureById(Long lectureId) {
        return lectureRepository.findById(lectureId);
    }

    public List<Lecture> getLecturesByIds(List<Long> lectureIds) {

        if (lectureIds == null || lectureIds.isEmpty()) {
            throw new IllegalArgumentException("강의 ID 리스트는 비어있을 수 없습니다.");
        }

        return lectureRepository.findAllById(lectureIds);
    }

    @Transactional
    public Lecture updateLecture(Long lectureId, UpdateLectureRequestDTO updateLectureDTO) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResourceNotFoundException("수정할 강의를 찾을 수 없습니다."));

        lecture.setLectureName(updateLectureDTO.getLectureName());
        lecture.setPrice(updateLectureDTO.getPrice());
        lecture.setTeacherName(updateLectureDTO.getTeacherName()); // 선생님 이름 수정
        lecture.setLectureStatus(updateLectureDTO.getLectureStatus()); // 강의 상태값 수정

        // 스케쥴 업데이트
        List<LectureSchedule> updatedSchedules = updateLectureDTO.getLectureSchedules().stream()
                .map(dto -> {
                    LectureSchedule schedule = new LectureSchedule();
                    schedule.setDay(dto.getDay());
                    schedule.setStartTime(dto.getStartTime());
                    schedule.setEndTime(dto.getEndTime());
                    schedule.setLecture(lecture);
                    return schedule;
                })
                .collect(Collectors.toList());

        // 기존 스케쥴 삭제
        lecture.getLectureSchedules().clear();
        // 새 스케쥴 추가
        lecture.getLectureSchedules().addAll(updatedSchedules);

        return lectureRepository.save(lecture);
    }

    @Transactional
    public void deleteLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResourceNotFoundException("삭제할 강의를 찾을 수 없습니다."));
        lectureRepository.delete(lecture);
    }


    public List<Lecture> searchLectures(SearchLectureDTO criteria) {
        return lectureRepository.searchLectures(
                criteria.getLectureName(),
                criteria.getTeacherName(),
                criteria.getLectureStatus()
        );

    }
}








