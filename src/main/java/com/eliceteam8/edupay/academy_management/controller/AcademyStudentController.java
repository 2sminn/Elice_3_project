package com.eliceteam8.edupay.academy_management.controller;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.CreateStudentRequestDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.SearchStudentDTO;
import com.eliceteam8.edupay.academy_management.lecture.dto.request.UpdateStudentRequestDTO;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import com.eliceteam8.edupay.academy_management.service.AcademyService;
import com.eliceteam8.edupay.academy_management.service.AcademyStudentService;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.eliceteam8.edupay.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academy-students")
public class AcademyStudentController {

    @Autowired
    private AcademyStudentService academyStudentService;

    @Autowired
    private UserService userService;

    @Autowired
    private AcademyService academyService;

    @GetMapping
    public ResponseEntity<List<AcademyStudentResponseDTO>> getAllAcademyStudents() {
            //@RequestParam(name = "page", defaultValue = "0") int page,
            //@RequestParam(name = "size", defaultValue = "10") int size) {
        //Pageable pageable = PageRequest.of(page, size);
        List<AcademyStudentResponseDTO> students = academyStudentService.getAllAcademyStudents();
        return ResponseEntity.ok(students);
        //return academyStudentService.getAllAcademyStudents();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<AcademyStudentResponseDTO> getStudentById(@PathVariable("studentId") Long id) {
        AcademyStudentResponseDTO academyStudent = academyStudentService.getStudentById(id);
        //AcademyStudentResponseDTO responseDTO = new AcademyStudentResponseDTO();
        return ResponseEntity.ok(academyStudent);
    }


    @PostMapping("/search")
    public ResponseEntity<List<AcademyStudentResponseDTO>> searchStudents(
            /*@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,*/
            @RequestBody SearchStudentDTO criteria) {
        //Pageable pageable = PageRequest.of(page, size);
        List<AcademyStudentResponseDTO> students = academyStudentService.searchStudents(criteria);
        return ResponseEntity.ok(students);
    }

    @PostMapping("")
    public ResponseEntity<AcademyStudent> createStudent(@Valid @RequestBody CreateStudentRequestDTO academyStudent) {
        AcademyStudent createdStudent = academyStudentService.createStudent(academyStudent);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<AcademyStudent> updateStudent(@PathVariable("studentId") Long id, @RequestBody UpdateStudentRequestDTO studentDetails) {
        AcademyStudent updatedStudent = academyStudentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long id) {
        academyStudentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
