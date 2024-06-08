package com.eliceteam8.edupay.point.repository;

import com.eliceteam8.edupay.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    List<PointHistory> findByUserId(Long userId);
}
