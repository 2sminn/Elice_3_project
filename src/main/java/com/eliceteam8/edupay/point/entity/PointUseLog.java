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
public class PointUseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long point;
    private Instant createdAt;

    @Builder
    public PointUseLog(Long userId, Long point, Instant createdAt) {
        this.userId = userId;
        this.point = point;
        this.createdAt = createdAt;
    }
}
