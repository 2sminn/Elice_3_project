package com.eliceteam8.edupay.get_cost.repository;

import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentPaymentStatusRepository extends JpaRepository<StudentPaymentStatus, Long> {
    Page<StudentPaymentStatus> findByBill_StatusAndUpdatedAtBetween(Status status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByUpdatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByStudent_StudentNameAndUpdatedAtBetween(String studentName, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByStudent_PhoneNumberAndUpdatedAtBetween(String phoneNumber, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<StudentPaymentStatus> findByBill_StatusAndUpdatedAtBetweenAndStudent_StudentName(Status status, LocalDateTime startDate, LocalDateTime endDate, String studentName, Pageable pageable);
    Page<StudentPaymentStatus> findByBill_StatusAndUpdatedAtBetweenAndStudent_PhoneNumber(Status status, LocalDateTime startDate, LocalDateTime endDate, String phoneNumber, Pageable pageable);

    Page<StudentPaymentStatus> findByStudent_StudentName(String studentName, Pageable pageable);
    Page<StudentPaymentStatus> findByStudent_PhoneNumber(String phoneNumber, Pageable pageable);
    Page<StudentPaymentStatus> findByBill_StatusAndStudent_StudentName(Status status, String studentName, Pageable pageable);
    Page<StudentPaymentStatus> findByBill_StatusAndStudent_PhoneNumber(Status status, String phoneNumber, Pageable pageable);
    Page<StudentPaymentStatus> findByBill_Status(Status status, Pageable pageable);

//    @Query("SELECT new com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto(YEAR(s.updatedAt), MONTH(s.updatedAt), COUNT(s), SUM(b.totalPrice)) " +
//            "FROM StudentPaymentStatus s JOIN s.bill b " +
//            "WHERE YEAR(s.updatedAt) = :year AND MONTH(s.updatedAt) = :month " +
//            "GROUP BY YEAR(s.updatedAt), MONTH(s.updatedAt)")
//    List<PaymentStatisticsDto> findStatisticsByYearAndMonth(int year, int month);

}
