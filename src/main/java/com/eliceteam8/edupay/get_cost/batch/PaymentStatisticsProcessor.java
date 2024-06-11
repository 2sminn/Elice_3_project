//package com.eliceteam8.edupay.get_cost.batch;
//
//import java.time.YearMonth;
//
//import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto;
//import org.springframework.batch.item.ItemProcessor;
//import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class PaymentStatisticsProcessor implements ItemProcessor<StudentPaymentStatus, PaymentStatisticsDto> {
//
//    @Override
//    public PaymentStatisticsDto process(StudentPaymentStatus item) throws Exception {
//        YearMonth yearMonth = YearMonth.from(item.getUpdatedAt());
//        return PaymentStatisticsDto.builder()
//                .yearMonth(yearMonth)
//                .totalCount(1)
//                .totalAmount(item.getBill().getTotalPrice())
//                .build();
//    }
//}