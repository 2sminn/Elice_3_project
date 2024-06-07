package com.eliceteam8.edupay.receipt.repository;

import com.eliceteam8.edupay.receipt.dto.ReceiptDto;
import com.eliceteam8.edupay.receipt.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {
    List<ReceiptEntity>findByStudentIdAndReceiptDateBetween(Long studentId, LocalDateTime startDate, LocalDateTime endDate);
}
