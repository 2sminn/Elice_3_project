package com.eliceteam8.edupay.get_cost.controller;

import com.eliceteam8.edupay.get_cost.dto.*;
import com.eliceteam8.edupay.get_cost.service.PaymentStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentStatisticsController {

    @Autowired
    private PaymentStatisticsService service;

    @GetMapping("/statistics/payments")
    public PaymentStatisticsResponseDto getPaymentStatistics(@RequestBody PaymentStatisticsRequestDto requestDto) {
        return service.getPaymentStatistics(requestDto);
    }
}