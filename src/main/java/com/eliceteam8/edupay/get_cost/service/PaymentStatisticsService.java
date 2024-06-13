//package com.eliceteam8.edupay.get_cost.service;
//
//import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto;
//import com.eliceteam8.edupay.get_cost.repository.StudentPaymentStatusRepository;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PaymentStatisticsService {
//
//    private final StudentPaymentStatusRepository studentPaymentStatusRepository;
//
//    @Cacheable(value = "paymentStatistics", key = "#year + '-' + #month")
//    public List<PaymentStatisticsDto> getStatistics(int year, int month) {
//        return studentPaymentStatusRepository.findStatisticsByYearAndMonth(year, month);
//    }
//}