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
public class PointRechargeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long point;
    private String paymentUid;
    private Instant createdAt;
    private boolean canRefund;

    @Builder
    public PointRechargeLog(Long point, Long userId, String paymentUid, Instant createdAt) {
        this.point = point;
        this.userId = userId;
        this.paymentUid = paymentUid;
        this.createdAt = createdAt;
        this.canRefund = true;
    }

    public void setRefunded() {
        this.canRefund = false;
    }
}
