package com.eliceteam8.edupay.get_cost.entity;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
import com.eliceteam8.edupay.payment.entity.PaymentHistory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "student_payment_status")
public class StudentPaymentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentStatusId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private AcademyStudent student;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentHistory payment;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public StudentPaymentStatus(AcademyStudent student, Bill bill, PaymentHistory payment, LocalDateTime updatedAt) {
        this.student = student;
        this.bill = bill;
        this.payment = payment;
        this.updatedAt = updatedAt;
    }

    public static StudentPaymentStatus fromRequestDto(StudentPaymentStatusRequestDto dto, AcademyStudent student, Bill bill, PaymentHistory payment) {
        return StudentPaymentStatus.builder()
                .student(student)
                .bill(bill)
                .payment(payment)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public StudentPaymentStatusResponseDto toResponseDto() {
        return StudentPaymentStatusResponseDto.builder()
                .paymentStatusId(this.paymentStatusId)
                .studentId(this.student.getId())
                .studentName(this.student.getStudentName())
                .birthDate(this.student.getBirthDate())
                .billId(this.bill.getId())
                .paymentId(this.payment.getId())
                .billStatus(this.bill.getStatus())
                .updatedAt(this.updatedAt)
                .totalPrice(this.bill.getTotalPrice())
                .build();
    }
}