package com.eliceteam8.edupay.point.service;

import com.eliceteam8.edupay.point.dto.PointHistoryDTO;
import com.eliceteam8.edupay.point.entity.PointHistory;
import com.eliceteam8.edupay.point.repository.PointHistoryRepository;
import com.eliceteam8.edupay.user.entity.User;
import com.eliceteam8.edupay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointService {

    private final UserRepository userRepository;
    private final PointHistoryRepository pointHistoryRepository;

    public Long getPoint(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::getPoint)
                .orElse(0L);
    }

    public List<PointHistory> getPointHistory(Long userId) {
        return pointHistoryRepository.findByUserId(userId);
    }

    public void savePoint(PointHistoryDTO pointHistoryDTO) {
        PointHistory pointHistory = PointHistory.builder()
                .userId(pointHistoryDTO.getUserId())
                .point(pointHistoryDTO.getPoint())
                .paymentUid(pointHistoryDTO.getImpUid())
                .createdAt(Instant.now())
                .build();

        pointHistoryRepository.save(pointHistory);

        User user = userRepository.findById(pointHistoryDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        user.addPoint(pointHistoryDTO.getPoint());

        userRepository.save(user);
    }
}
