package com.eliceteam8.edupay.payment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long billId;
    private String paymentUid;
    private Instant createdAt;

    @Builder
    public PaymentHistory(String paymentUid, Long billId, Instant createdAt) {
        this.paymentUid = paymentUid;
        this.billId = billId;
        this.createdAt = createdAt;
    }
}
