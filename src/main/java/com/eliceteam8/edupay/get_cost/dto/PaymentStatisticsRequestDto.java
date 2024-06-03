package com.eliceteam8.edupay.get_cost.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentStatisticsRequestDto {
    private Long academyId;
    private BigDecimal totalPaid;
    private BigDecimal totalUnpaid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
}