package com.eliceteam8.edupay.get_cost.repository;

import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentPaymentStatusRepository extends JpaRepository<StudentPaymentStatus, Long> {
    List<StudentPaymentStatus> findByStudentId(Long studentId);
    List<StudentPaymentStatus> findByPaymentStatus(String paymentStatus);
}