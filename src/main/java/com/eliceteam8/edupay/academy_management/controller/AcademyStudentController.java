package com.eliceteam8.edupay.academy_management.controller;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import com.eliceteam8.edupay.academy_management.service.AcademyStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academyStudents")
public class AcademyStudentController {

    @Autowired
    private AcademyStudentService academyStudentService;

    @GetMapping
    public List<AcademyStudentResponseDTO> getAllAcademyStudents() {
        return academyStudentService.getAllAcademyStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademyStudentResponseDTO> getStudentById(@PathVariable Long id) {
        AcademyStudentResponseDTO academyStudent = academyStudentService.getStudentById(id);
        return ResponseEntity.ok(academyStudent);
    }

    @PostMapping
    public ResponseEntity<AcademyStudent> createStudent(@RequestBody AcademyStudent academyStudent) {
        AcademyStudent createdStudent = academyStudentService.createStudent(academyStudent);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademyStudent> updateStudent(@PathVariable Long id, @RequestBody AcademyStudent studentDetails) {
        AcademyStudent updatedStudent = academyStudentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        academyStudentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
