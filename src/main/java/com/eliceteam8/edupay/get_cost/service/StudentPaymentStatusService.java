package com.eliceteam8.edupay.get_cost.service;

import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import com.eliceteam8.edupay.get_cost.repository.StudentPaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentPaymentStatusService {

    @Autowired
    private StudentPaymentStatusRepository repository;

    public StudentPaymentStatusResponseDto updatePaymentStatus(Long orderId, StudentPaymentStatusRequestDto requestDto) {
        StudentPaymentStatus paymentStatus = repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        paymentStatus.setPaymentStatus(requestDto.getPaymentStatus());
        paymentStatus.setUpdatedAt(LocalDateTime.now());
        repository.save(paymentStatus);
        return paymentStatus.toResponseDto();
    }

    public List<StudentPaymentStatusResponseDto> getPaymentsByStudentId(Long studentId) {
        return repository.findAllByStudentId(studentId).stream()
                .map(StudentPaymentStatus::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<StudentPaymentStatusResponseDto> getUnpaidInvoices() {
        return repository.findAllByPaymentStatus("unpaid").stream()
                .map(StudentPaymentStatus::toResponseDto)
                .collect(Collectors.toList());
    }
}