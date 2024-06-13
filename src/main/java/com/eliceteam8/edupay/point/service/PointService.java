package com.eliceteam8.edupay.point.service;

import com.eliceteam8.edupay.point.dto.PointLogDTO;
import com.eliceteam8.edupay.point.entity.PointRechargeLog;
import com.eliceteam8.edupay.point.entity.PointUseLog;
import com.eliceteam8.edupay.point.repository.PointRechargeLogRepository;
import com.eliceteam8.edupay.point.repository.PointUseLogRepository;
import com.eliceteam8.edupay.user.entity.User;
import com.eliceteam8.edupay.user.repository.UserRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointService {

    private final UserRepository userRepository;
    private final PointRechargeLogRepository pointRechargeLogRepository;
    private final PointUseLogRepository pointUseLogRepository;
    private final IamportClient iamportClient;

    public Long getPoint(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getPoint)
                .orElse(0L);
    }

    public Page<PointRechargeLog> getPointRechargeLog(Long userId, Pageable pageable) {
        return pointRechargeLogRepository.findByUserId(userId, pageable);
    }

    public Page<PointUseLog> getPointUseLog(Long userId, Pageable pageable) {
        return pointUseLogRepository.findByUserId(userId, pageable);
    }

    public void savePoint(PointLogDTO pointLogDTO) {
        PointRechargeLog pointRechargeLog = PointRechargeLog.builder()
                .userId(pointLogDTO.getUserId())
                .point(pointLogDTO.getPoint())
                .paymentUid(pointLogDTO.getImpUid())
                .createdAt(Instant.now())
                .build();

        pointRechargeLogRepository.save(pointRechargeLog);

        User user = userRepository.findById(pointLogDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다."));

        user.addPoint(pointLogDTO.getPoint());

        userRepository.save(user);
    }

    public void usePoint(PointLogDTO pointLogDTO) {
        PointUseLog pointUseLog = PointUseLog.builder()
                .userId(pointLogDTO.getUserId())
                .point(pointLogDTO.getPoint())
                .createdAt(Instant.now())
                .build();

        User user = userRepository.findById(pointUseLog.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));

        validatePoint(user.getPoint(), pointLogDTO.getPoint());

        user.usePoint(pointLogDTO.getPoint());

        pointUseLogRepository.save(pointUseLog);
        userRepository.save(user);
    }

    public void refundPoint(PointLogDTO pointLogDTO) throws IamportResponseException, IOException {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(pointLogDTO.getImpUid());
        Long refundAmount = payment.getResponse().getAmount().longValue();
        User user = userRepository.findById(pointLogDTO.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다."));

        validatePoint(user.getPoint(), refundAmount);

        CancelData cancelData = new CancelData(payment.getResponse().getImpUid(), true);
        iamportClient.cancelPaymentByImpUid(cancelData);
        user.usePoint(refundAmount);

        userRepository.save(user);
    }

    private void validatePoint(Long currentPoint, Long point) {
        if (point > currentPoint) {
            throw new RuntimeException("포인트가 부족합니다.");
        }
    }
}
