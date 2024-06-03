package com.eliceteam8.edupay.get_cost.repository;

import com.eliceteam8.edupay.get_cost.entity.PaymentStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatisticsRepository extends JpaRepository<PaymentStatistics, Long> {
}