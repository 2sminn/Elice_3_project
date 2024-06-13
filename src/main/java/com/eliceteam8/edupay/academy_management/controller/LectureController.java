package com.eliceteam8.edupay.academy_management.controller;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.entity.LectureSchedule;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.CreateLectureRequestDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.SearchLectureDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.UpdateLectureRequestDTO;
import com.eliceteam8.edupay.academy_management.response.LectureDTO;
import com.eliceteam8.edupay.academy_management.service.LectureService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    @Autowired
    private LectureService lectureService;

    /*@PostMapping("")
    public Lecture createLecture(@Valid @RequestBody CreateLectureRequestDTO lectureDTO) {
        return lectureService.createLecture(lectureDTO);
    }*/

    @PostMapping("")
    public ResponseEntity<Lecture> createLecture(@Valid @RequestBody CreateLectureRequestDTO lectureDTO) {
        Lecture lecture = lectureService.createLecture(lectureDTO);
        return ResponseEntity.ok(lecture);
    }

    @GetMapping
    /*public ResponseEntity<Page<LectureDTO>> getAllLectures(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        Page<LectureDTO> lectures = lectureService.getAllLectures(pageable);
        return ResponseEntity.ok(lectures);
    }*/

    public ResponseEntity<List<LectureDTO>> getAllLectures() {
        List<LectureDTO> lectures = lectureService.getAllLectures();
        return ResponseEntity.ok(lectures);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable("lectureId") Long id) {
        Lecture lecture = lectureService.getLectureById(id)
                .orElseThrow(() -> new RuntimeException("강의를 찾을 수 없습니다."));
        return ResponseEntity.ok().body(lecture);
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable("lectureId") Long id, @Valid @RequestBody UpdateLectureRequestDTO updateLectureRequestDTO) {
        //UpdateLectureRequestDTO UpdateLectureRequestDTO;
        Lecture updatedLecture = lectureService.updateLecture(id, updateLectureRequestDTO);
        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<Void> deleteLecture(@PathVariable("lectureId") Long id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }

    //학생이 수강중인 강의목록 조회
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Lecture>> getLecturesByStudentId(@PathVariable("studentId") Long studentId) {
        List<Lecture> lectures = lectureService.getLecturesByStudentId(studentId);
        return ResponseEntity.ok(lectures);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Lecture>> searchLectures(@Valid @RequestBody SearchLectureDTO criteria) {
        List<Lecture> lectures = lectureService.searchLectures(criteria);
        return ResponseEntity.ok(lectures);
    }

}

