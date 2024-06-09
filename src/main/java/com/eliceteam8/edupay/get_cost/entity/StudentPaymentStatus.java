package com.eliceteam8.edupay.get_cost.entity;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.bill.domain.Bill;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private AcademyStudent student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentHistory payment;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public StudentPaymentStatus(AcademyStudent student, Bill bill, PaymentHistory payment, LocalDateTime updatedAt) {
        this.student = student;
        this.bill = bill;
        this.payment = payment;
        this.updatedAt = updatedAt;
    }
}