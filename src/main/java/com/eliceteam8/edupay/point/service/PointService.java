package com.eliceteam8.edupay.point.service;

import com.eliceteam8.edupay.point.dto.PointHistoryDTO;
import com.eliceteam8.edupay.point.entity.Point;
import com.eliceteam8.edupay.point.entity.PointHistory;
import com.eliceteam8.edupay.point.repository.PointHistoryRepository;
import com.eliceteam8.edupay.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

    public Long getPoint(Long userId) {
        return pointRepository.findByUserId(userId)
                .map(Point::getPoint)
                .orElse(0L);
    }

    public List<PointHistory> getPointHistory(Long userId) {
        return pointHistoryRepository.findByUserId(userId);
    }

    public void savePointHistory(PointHistoryDTO pointHistoryDTO) {
        PointHistory pointHistory = PointHistory.builder()
                .userId(pointHistoryDTO.getUserId())
                .point(pointHistoryDTO.getPoint())
                .paymentUid(pointHistoryDTO.getImpUid())
                .createdAt(Instant.now())
                .build();
    }
}
