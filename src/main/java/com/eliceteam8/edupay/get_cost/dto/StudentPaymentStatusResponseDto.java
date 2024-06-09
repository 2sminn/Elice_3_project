package com.eliceteam8.edupay.get_cost.dto;

import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentPaymentStatusResponseDto {
    private Long paymentStatusId;
    private Long studentId;
    private String studentName;
    private String birthDate;
    private Long billId;
    private Long paymentId;
    private Status billStatus;
    private LocalDateTime updatedAt;
    private long totalPrice;

    public static StudentPaymentStatusResponseDto fromEntity(StudentPaymentStatus entity) {
        if (entity == null) return null;

        return StudentPaymentStatusResponseDto.builder()
                .paymentStatusId(entity.getPaymentStatusId())
                .studentId(entity.getStudent().getId())
                .studentName(entity.getStudent().getStudentName())
                .birthDate(entity.getStudent().getBirthDate())
                .billId(entity.getBill().getId())
                .paymentId(entity.getPayment().getId())
                .billStatus(entity.getBill().getStatus())
                .updatedAt(entity.getUpdatedAt())
                .totalPrice(entity.getBill().getTotalPrice())
                .build();
    }
}