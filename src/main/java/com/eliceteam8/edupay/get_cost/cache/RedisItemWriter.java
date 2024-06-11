//package com.eliceteam8.edupay.get_cost.cache;
//
//import java.util.List;
//
//import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.data.redis.core.RedisTemplate;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class RedisItemWriter<P> implements ItemWriter<PaymentStatisticsDto> {
//
//    private final RedisTemplate<String, PaymentStatisticsDto> redisTemplate;
//
//    @Override
//    public void write(List<? extends PaymentStatisticsDto> items) throws Exception {
//        for (PaymentStatisticsDto item : items) {
//            String key = "PaymentStatisticsDto:" + item.getYearMonth();
//            redisTemplate.opsForValue().set(key, item);
//        }
//    }
//}