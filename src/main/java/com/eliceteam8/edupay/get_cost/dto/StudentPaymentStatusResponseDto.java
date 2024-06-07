package com.eliceteam8.edupay.get_cost.dto;

import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentPaymentStatusResponseDto {
    private Long paymentStatusId;
    private Long studentId;
    private Long billId;
    private Long paymentId;
    private String billStatus;
    private LocalDateTime updatedAt;

    public static StudentPaymentStatusResponseDto fromEntity(StudentPaymentStatus entity) {
        if (entity == null) return null;

        return StudentPaymentStatusResponseDto.builder()
                .paymentStatusId(entity.getPaymentStatusId())
                .studentId(entity.getStudent().getId())
                .billId(entity.getBill().getId())
                .paymentId(entity.getPayment().getId())
                .billStatus(entity.getBill().getStatus().name())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}