package com.eliceteam8.edupay.point.repository;

import com.eliceteam8.edupay.point.entity.PointUseLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUseLogRepository extends JpaRepository<PointUseLog, Long> {
    Page<PointUseLog> findByUserId(Long userId, Pageable pageable);
}

