//package com.eliceteam8.edupay.get_cost.controller;
//
//import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto;
//import com.eliceteam8.edupay.get_cost.service.PaymentStatisticsService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/statistics")
//@RequiredArgsConstructor
//public class PaymentStatisticsController {
//
//    private final PaymentStatisticsService paymentStatisticsService;
//
//    @GetMapping
//    public ResponseEntity<List<PaymentStatisticsDto>> getStatistics(
//            @RequestParam(name = "year") int year, @RequestParam(name = "month") int month) {
//        return ResponseEntity.ok(paymentStatisticsService.getStatistics(year, month));
//    }
//}