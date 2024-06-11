//package com.eliceteam8.edupay.get_cost.controller;
//
//import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto;
//import com.eliceteam8.edupay.get_cost.service.PaymentStatisticsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class PaymentStatisticsController {
//
//    @Autowired
//    private PaymentStatisticsService paymentStatisticsService;
//
//    @GetMapping("/payment-statistics")
//    public ResponseEntity<PaymentStatisticsDto> getStatistics(@RequestParam int year, @RequestParam int month) {
//        return ResponseEntity.of(paymentStatisticsService.getStatistics(year, month));
//    }
//}