package com.eliceteam8.edupay.point.repository;

import com.eliceteam8.edupay.point.entity.PointUseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointUseLogRepository extends JpaRepository<PointUseLog, Long> {
    List<PointUseLog> findByUserId(Long userId);
}

