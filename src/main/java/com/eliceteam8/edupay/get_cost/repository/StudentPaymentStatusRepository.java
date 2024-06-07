package com.eliceteam8.edupay.get_cost.repository;

import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StudentPaymentStatusRepository extends JpaRepository<StudentPaymentStatus, Long> {
    Page<StudentPaymentStatus> findByPaymentStatusAndUpdatedAtBetween(String paymentStatus, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByUpdatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByStudent_StudentNameAndUpdatedAtBetween(String studentName, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByStudent_PhoneNumberAndUpdatedAtBetween(String phoneNumber, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByPaymentStatusAndUpdatedAtBetweenAndStudent_StudentName(String paymentStatus, LocalDateTime startDate, LocalDateTime endDate, String studentName, Pageable pageable);
    Page<StudentPaymentStatus> findByPaymentStatusAndUpdatedAtBetweenAndStudent_PhoneNumber(String paymentStatus, LocalDateTime startDate, LocalDateTime endDate, String phoneNumber, Pageable pageable);
}