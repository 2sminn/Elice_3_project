package com.eliceteam8.edupay.get_cost.service;

import com.eliceteam8.edupay.get_cost.dto.*;
import com.eliceteam8.edupay.get_cost.entity.PaymentStatistics;
import com.eliceteam8.edupay.get_cost.repository.PaymentStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatisticsService {

    @Autowired
    private PaymentStatisticsRepository repository;

    public PaymentStatisticsResponseDto getPaymentStatistics(PaymentStatisticsRequestDto requestDto) {
        // Assuming that the statistics are calculated and stored separately
        PaymentStatistics entity = new PaymentStatistics();
        // Logic to calculate statistics based on requestDto
        // This could be a complex logic to calculate total paid/unpaid etc.
        return entity.toResponseDto();
    }
}