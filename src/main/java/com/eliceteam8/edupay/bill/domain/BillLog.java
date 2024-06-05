package com.eliceteam8.edupay.bill.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill_log")
@Getter
@NoArgsConstructor
public class BillLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "remaining_bills")
    @ColumnDefault("0")
    private Long remainingBills; // 남은 발송 개수

    @CreationTimestamp
    // INSERT 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @OneToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    private Bill bill;
}
