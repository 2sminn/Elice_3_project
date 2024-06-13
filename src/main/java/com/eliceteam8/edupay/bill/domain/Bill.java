package com.eliceteam8.edupay.bill.domain;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
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
    @ColumnDefault("'BEFORE'")
    private Status status; // 청구서 상태

    private String message;

    @ManyToOne
    @JoinColumn(name = "academy_id")
    @JsonBackReference
    private Academy academy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private AcademyStudent student;

    @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL)
    @JsonBackReference
    private BillLog billLog;

    @OneToMany(mappedBy = "bill")
    @JsonBackReference
    private List<StudentPaymentStatus> paymentStatuses = new ArrayList<>();

    // 결제가 완료되었을 때 청구서 상태를 'PAID'로 바꾸는 메서드
    public void setStatusToPaid() {
        this.status = Status.PAID;
    }
}
