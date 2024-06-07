package com.eliceteam8.edupay.academy_management.controller;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import com.eliceteam8.edupay.academy_management.service.AcademyStudentService;
import com.eliceteam8.edupay.global.exception.ApiError;
import com.eliceteam8.edupay.global.exception.DuplicateStudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/academy-students")
public class AcademyStudentController {

    @Autowired
    private AcademyStudentService academyStudentService;

    @GetMapping
    public List<AcademyStudentResponseDTO> getAllAcademyStudents() {
        return academyStudentService.getAllAcademyStudents();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<AcademyStudentResponseDTO> getStudentById(@PathVariable("studentId") Long id) {
        AcademyStudentResponseDTO academyStudent = academyStudentService.getStudentById(id);
       return ResponseEntity.ok(academyStudent);
    }

    @PostMapping("")
    public ResponseEntity<AcademyStudent> createStudent(@RequestBody AcademyStudent academyStudent) {
        AcademyStudent createdStudent = academyStudentService.createStudent(academyStudent);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<AcademyStudent> updateStudent(@PathVariable("studentId") Long id, @RequestBody AcademyStudent studentDetails) {
        AcademyStudent updatedStudent = academyStudentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long id) {
        academyStudentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
