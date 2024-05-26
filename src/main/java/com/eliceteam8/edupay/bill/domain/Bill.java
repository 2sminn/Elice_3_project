package com.eliceteam8.edupay.bill.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    @ColumnDefault("0")
    private long totalPrice;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @CreationTimestamp
    // INSERT 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    // UPDATE 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "academy_id")
    private long academyId;

    @Column(name = "student_id")
    private long studentId;
}
