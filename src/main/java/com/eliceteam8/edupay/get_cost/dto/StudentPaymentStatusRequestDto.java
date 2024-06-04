package com.eliceteam8.edupay.get_cost.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentPaymentStatusRequestDto {
    private Long studentId;
    private Long orderId;
    private String paymentStatus;
}