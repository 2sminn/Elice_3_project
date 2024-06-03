package com.eliceteam8.edupay.get_cost.controller;

import com.eliceteam8.edupay.get_cost.dto.*;
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

    @GetMapping("/students/{studentId}/payments")
    public List<StudentPaymentStatusResponseDto> getPaymentsByStudentId(@PathVariable Long studentId) {
        return service.getPaymentsByStudentId(studentId);
    }

    @GetMapping("/invoices/unpaid")
    public List<StudentPaymentStatusResponseDto> getUnpaidInvoices() {
        return service.getUnpaidInvoices();
    }
}