package com.eliceteam8.edupay.point.repository;

import com.eliceteam8.edupay.point.entity.PointRechargeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRechargeLogRepository extends JpaRepository<PointRechargeLog, Long> {
    List<PointRechargeLog> findByUserId(Long userId);
}
