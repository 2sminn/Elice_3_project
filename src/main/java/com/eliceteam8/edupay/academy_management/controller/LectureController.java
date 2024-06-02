package com.eliceteam8.edupay.academy_management.controller;

import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.CreateLectureRequestDTO;
import com.eliceteam8.edupay.academy_management.response.LectureDTO;
import com.eliceteam8.edupay.academy_management.service.LectureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    @Autowired
    private LectureService lectureService;

    @PostMapping("/create")
    public Lecture createLecture(@Valid @RequestBody CreateLectureRequestDTO lectureDTO) {
        return lectureService.createLecture(lectureDTO);
    }

    @GetMapping
    public List<Lecture> getAllLectures() {
        return lectureService.getAllLectures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable Long id) {
        Lecture lecture = lectureService.getLectureById(id)
                .orElseThrow(() -> new RuntimeException("강의를 찾을 수 없습니다."));
        return ResponseEntity.ok().body(lecture);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable Long id, @Valid @RequestBody LectureDTO lectureDTO) {
        Lecture updatedLecture = lectureService.updateLecture(id, lectureDTO);
        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }
}

