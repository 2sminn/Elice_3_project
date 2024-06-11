//package com.eliceteam8.edupay.get_cost.batch;
//
//import com.eliceteam8.edupay.get_cost.dto.PaymentStatisticsDto;
//import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
//import com.eliceteam8.edupay.get_cost.cache.RedisItemWriter;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.data.builder.RedisItemWriterBuilder;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final EntityManagerFactory entityManagerFactory;
//
//    public BatchConfiguration(JobBuilderFactory jobBuilderFactory,
//                              StepBuilderFactory stepBuilderFactory,
//                              EntityManagerFactory entityManagerFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Bean
//    public Job calculateStatisticsJob(JobCompletionNotificationListener listener, Step step1) {
//        return jobBuilderFactory.get("calculateStatisticsJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(step1)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1(ItemReader<StudentPaymentStatus> reader,
//                      ItemProcessor<StudentPaymentStatus, PaymentStatisticsDto> processor,
//                      RedisItemWriter<PaymentStatisticsDto> writer) {
//        return stepBuilderFactory.get("step1")
//                .<StudentPaymentStatus, PaymentStatisticsDto>chunk(10)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }
//
//    @Bean
//    public JpaPagingItemReader<StudentPaymentStatus> reader() {
//        return new JpaPagingItemReaderBuilder<StudentPaymentStatus>()
//                .name("studentPaymentStatusReader")
//                .entityManagerFactory(entityManagerFactory)
//                .queryString("SELECT s FROM StudentPaymentStatus s WHERE s.updatedAt BETWEEN :startDate AND :endDate")
//                .build();
//    }
//
//    @Bean
//    public ItemProcessor<StudentPaymentStatus, PaymentStatisticsDto> processor() {
//        return new PaymentStatisticsProcessor();
//    }
//
//    @Bean
//    public RedisItemWriter<PaymentStatisticsDto> writer(RedisTemplate<String, PaymentStatisticsDto> redisTemplate) {
//        return new RedisItemWriterBuilder<PaymentStatisticsDto>()
//                .redisTemplate(redisTemplate)
//                .build();
//    }
//}