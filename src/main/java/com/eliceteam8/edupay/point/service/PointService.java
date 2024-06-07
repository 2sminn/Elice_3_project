package com.eliceteam8.edupay.point.service;

import com.eliceteam8.edupay.point.dto.PointDTO;
import com.eliceteam8.edupay.point.entity.Point;

import java.time.Instant;

public class PointService {

    public void savePoint(PointDTO pointDTO) {
        Point point = Point.builder()
                .userId(pointDTO.getUserId())
                .point(pointDTO.getPoint())
                .paymentUid(pointDTO.getImpUid())
                .createdAt(Instant.now())
                .build();
    }
}
