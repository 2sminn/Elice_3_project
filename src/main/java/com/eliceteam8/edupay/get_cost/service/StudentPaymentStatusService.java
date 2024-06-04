package com.eliceteam8.edupay.get_cost.service;

import com.eliceteam8.edupay.get_cost.dto.*;
import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import com.eliceteam8.edupay.get_cost.repository.StudentPaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentPaymentStatusService {

    @Autowired
    private StudentPaymentStatusRepository repository;

    public StudentPaymentStatusResponseDto updatePaymentStatus(Long orderId, StudentPaymentStatusRequestDto requestDto) {
        StudentPaymentStatus entity = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        entity.setPaymentStatus(requestDto.getPaymentStatus());
        entity.setUpdatedAt(requestDto.getUpdatedAt());
        repository.save(entity);
        return entity.toResponseDto();
    }

    public List<StudentPaymentStatusResponseDto> getPaymentsByStudentId(Long studentId) {
        List<StudentPaymentStatus> entities = repository.findByStudentId(studentId);
        return entities.stream()
                .map(StudentPaymentStatus::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<StudentPaymentStatusResponseDto> getUnpaidInvoices() {
        List<StudentPaymentStatus> entities = repository.findByPaymentStatus("unpaid");
        return entities.stream()
                .map(StudentPaymentStatus::toResponseDto)
                .collect(Collectors.toList());
    }
}