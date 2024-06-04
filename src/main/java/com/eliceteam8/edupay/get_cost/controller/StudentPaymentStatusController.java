package com.eliceteam8.edupay.get_cost.controller;

import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
import com.eliceteam8.edupay.get_cost.service.StudentPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class StudentPaymentStatusController {

    @Autowired
    private StudentPaymentStatusService service;

    @PatchMapping("/invoices/{orderId}/status")
    public StudentPaymentStatusResponseDto updatePaymentStatus(
            @PathVariable Long orderId,
            @RequestBody StudentPaymentStatusRequestDto requestDto) {
        return service.updatePaymentStatus(orderId, requestDto);
    }

    @GetMapping("/{studentId}")
    public List<StudentPaymentStatusResponseDto> getPaymentsByStudentId(@PathVariable Long studentId) {
        return service.getPaymentsByStudentId(studentId);
    }

    @GetMapping("/invoices")
    public List<StudentPaymentStatusResponseDto> getUnpaidInvoices(@RequestParam(value = "unpaid", required = false) Boolean unpaid) {
        if (unpaid != null && unpaid) {
            return service.getUnpaidInvoices();
        }
        // 모든 인보이스 조회 로직 개발중
        return null;
    }
}