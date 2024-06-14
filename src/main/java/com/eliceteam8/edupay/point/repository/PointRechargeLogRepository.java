package com.eliceteam8.edupay.point.repository;

import com.eliceteam8.edupay.point.entity.PointRechargeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRechargeLogRepository extends JpaRepository<PointRechargeLog, Long> {
    Page<PointRechargeLog> findByUserId(Long userId, Pageable pageable);

    PointRechargeLog findByPaymentUid(String paymentId);
}
