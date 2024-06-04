package com.eliceteam8.edupay.get_cost.entity;

import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
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
    @Column(name = "payment_status_id")
    private Long paymentStatusId;

    @Column(nullable = false, name = "student_id")
    private Long studentId;

    @Column(nullable = false, name = "order_id")
    private Long orderId;

    @Column(nullable = false, name = "payment_status")
    private String paymentStatus;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    public static StudentPaymentStatus fromRequestDto(StudentPaymentStatusRequestDto dto) {
        return StudentPaymentStatus.builder()
                .studentId(dto.getStudentId())
                .orderId(dto.getOrderId())
                .paymentStatus(dto.getPaymentStatus())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public StudentPaymentStatusResponseDto toResponseDto() {
        return StudentPaymentStatusResponseDto.builder()
                .paymentStatusId(this.paymentStatusId)
                .studentId(this.studentId)
                .orderId(this.orderId)
                .paymentStatus(this.paymentStatus)
                .updatedAt(this.updatedAt)
                .build();
    }

    @Builder
    public StudentPaymentStatus(Long studentId, Long orderId, String paymentStatus, LocalDateTime updatedAt) {
        this.studentId = studentId;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.updatedAt = updatedAt;
    }
}