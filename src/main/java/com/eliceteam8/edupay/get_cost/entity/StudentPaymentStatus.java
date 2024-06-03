package com.eliceteam8.edupay.get_cost.entity;

import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_payment_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentStatusId;

    private Long studentId;
    private Long orderId;
    private String paymentStatus;
    private LocalDateTime updatedAt;

    // Convert DTO to Entity
    public static StudentPaymentStatus fromDto(StudentPaymentStatusRequestDto dto) {
        return StudentPaymentStatus.builder()
                .studentId(dto.getStudentId())
                .orderId(dto.getOrderId())
                .paymentStatus(dto.getPaymentStatus())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    // Convert Entity to DTO
    public StudentPaymentStatusResponseDto toResponseDto() {
        return StudentPaymentStatusResponseDto.builder()
                .paymentStatusId(this.paymentStatusId)
                .studentId(this.studentId)
                .orderId(this.orderId)
                .paymentStatus(this.paymentStatus)
                .updatedAt(this.updatedAt)
                .build();
    }
}