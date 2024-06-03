package com.eliceteam8.edupay.get_cost.entity;

import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsRequestDto;
import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statsId;
    private Long academyId;
    private BigDecimal totalPaid;
    private BigDecimal totalUnpaid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;

    // Convert DTO to Entity
    public static PaymentStatistics fromDto(PaymentStatisticsRequestDto dto) {
        return PaymentStatistics.builder()
                .academyId(dto.getAcademyId())
                .totalPaid(dto.getTotalPaid())
                .totalUnpaid(dto.getTotalUnpaid())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    // Convert Entity to DTO
    public PaymentStatisticsResponseDto toResponseDto() {
        return PaymentStatisticsResponseDto.builder()
                .statsId(this.statsId)
                .academyId(this.academyId)
                .totalPaid(this.totalPaid)
                .totalUnpaid(this.totalUnpaid)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .createdAt(this.createdAt)
                .build();
    }
}