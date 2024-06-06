package com.eliceteam8.edupay.payment.repository;

import com.eliceteam8.edupay.payment.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
}
