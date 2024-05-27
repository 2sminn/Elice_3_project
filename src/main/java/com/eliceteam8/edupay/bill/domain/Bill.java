package com.eliceteam8.edupay.bill.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    @ColumnDefault("0")
    private long totalPrice;

    @Column(name = "due_date")
    private LocalDateTime dueDate; // 청구서 마감 기한

    @CreationTimestamp
    // INSERT 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    // UPDATE 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status; // 청구서 상태

    private String message;

    //TODO: 연관관계 매핑
    @Column(name = "academy_id")
    private long academyId;

    @Column(name = "student_id")
    private long studentId;

    @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL)
    private BillLog billLog;

    public Bill(LocalDateTime dueDate, String message) {
        this.dueDate = dueDate;
        this.message = message;
    }
}
