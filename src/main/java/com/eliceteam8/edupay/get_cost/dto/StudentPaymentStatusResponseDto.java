package com.eliceteam8.edupay.get_cost.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentPaymentStatusResponseDto {
    private Long paymentStatusId;
    private Long studentId;
    private Long orderId;
    private String paymentStatus;
    private LocalDateTime updatedAt;
}