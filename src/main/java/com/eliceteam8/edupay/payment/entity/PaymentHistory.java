package com.eliceteam8.edupay.payment.entity;

import com.eliceteam8.edupay.payment.enumeration.PaymentHistoryType;
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

    @Enumerated(EnumType.STRING)
    private PaymentHistoryType paymentHistoryType;

    public void markHistoryType(PaymentHistoryType paymentHistoryType) {
        this.paymentHistoryType = paymentHistoryType;
    }

    public void updateHistoryInfo(String paymentUid, PaymentHistoryType paymentHistoryType) {
        this.paymentUid = paymentUid;
        this.paymentHistoryType = paymentHistoryType;
    }

    @Builder
    public PaymentHistory(String paymentUid, Long billId, Instant createdAt, PaymentHistoryType paymentHistoryType) {
        this.paymentUid = paymentUid;
        this.billId = billId;
        this.createdAt = createdAt;
        this.paymentHistoryType = paymentHistoryType;
    }
}
