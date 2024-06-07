package com.eliceteam8.edupay.get_cost.controller;

import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import com.eliceteam8.edupay.get_cost.service.StudentPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class StudentPaymentStatusController {

    private final StudentPaymentStatusService studentPaymentStatusService;

    @Autowired
    public StudentPaymentStatusController(StudentPaymentStatusService studentPaymentStatusService) {
        this.studentPaymentStatusService = studentPaymentStatusService;
    }

    // 전체 수납 내역 조회
    @GetMapping
    public ResponseEntity<Page<StudentPaymentStatusResponseDto>> getAllPayments(@RequestParam String status,
                                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_date,
                                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_date,
                                                                                @RequestParam(required = false) String studentName,
                                                                                @RequestParam(required = false) String phoneNumber,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(studentPaymentStatusService.getAllPayments(status, start_date, end_date, studentName, phoneNumber, page, size));
    }

    // 수납된 결제 내역 조회
    @GetMapping("/paid")
    public ResponseEntity<Page<StudentPaymentStatusResponseDto>> getPaidPayments(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_date,
                                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_date,
                                                                                 @RequestParam(required = false) String studentName,
                                                                                 @RequestParam(required = false) String phoneNumber,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(studentPaymentStatusService.getPaidPayments(start_date, end_date, studentName, phoneNumber, page, size));
    }

    // 미수납된 결제 내역 조회
    @GetMapping("/unpaid")
    public ResponseEntity<Page<StudentPaymentStatusResponseDto>> getUnpaidPayments(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_date,
                                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_date,
                                                                                   @RequestParam(required = false) String studentName,
                                                                                   @RequestParam(required = false) String phoneNumber,
                                                                                   @RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(studentPaymentStatusService.getUnpaidPayments(start_date, end_date, studentName, phoneNumber, page, size));
    }

    // 수납 상태 생성
    @PostMapping
    public ResponseEntity<StudentPaymentStatusResponseDto> createPaymentStatus(@RequestBody StudentPaymentStatusRequestDto requestDto) {
        StudentPaymentStatus createdStatus = studentPaymentStatusService.createPaymentStatus(requestDto);
        return ResponseEntity.ok(StudentPaymentStatusResponseDto.fromEntity(createdStatus));
    }

    // 수납 상태 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<StudentPaymentStatusResponseDto> updatePaymentStatus(@PathVariable Long id, @RequestBody StudentPaymentStatusRequestDto requestDto) {
        StudentPaymentStatus updatedStatus = studentPaymentStatusService.updatePaymentStatus(id, requestDto);
        return ResponseEntity.ok(StudentPaymentStatusResponseDto.fromEntity(updatedStatus));
    }

    // 수납 상태 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentStatus(@PathVariable Long id) {
        studentPaymentStatusService.deletePaymentStatus(id);
        return ResponseEntity.noContent().build();
    }

    // 단일 수납 상태 조회
    @GetMapping("/{id}")
    public ResponseEntity<StudentPaymentStatusResponseDto> getPaymentStatusById(@PathVariable Long id) {
        StudentPaymentStatus paymentStatus = studentPaymentStatusService.getPaymentStatusById(id);
        return ResponseEntity.ok(StudentPaymentStatusResponseDto.fromEntity(paymentStatus));
    }
}