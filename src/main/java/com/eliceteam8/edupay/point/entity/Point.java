package com.eliceteam8.edupay.point.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long point;
    private String paymentUid;
    private Instant createdAt;

    @Builder
    public Point(Long userId, Long point, String paymentUid, Instant createdAt) {
        this.userId = userId;
        this.point = point;
        this.paymentUid = paymentUid;
        this.createdAt = createdAt;
    }
}
