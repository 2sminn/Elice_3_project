//package com.eliceteam8.edupay.get_cost.service;
//
//import java.time.YearMonth;
//import java.util.Optional;
//
//import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PaymentStatisticsService {
//
//    @Autowired
//    private RedisTemplate<String, PaymentStatisticsDto> redisTemplate;
//
//    public Optional<PaymentStatisticsDto> getStatistics(int year, int month) {
//        YearMonth yearMonth = YearMonth.of(year, month);
//        String key = "PaymentStatisticsDto:" + yearMonth;
//        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
//    }
//}