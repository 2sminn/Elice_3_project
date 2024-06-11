package com.eliceteam8.edupay.academy_management.entity;

import com.eliceteam8.edupay.bill.domain.Bill;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "academy_student")
public class AcademyStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String studentName;

    @Column(unique = true)
    private String phoneNumber;

    @Column
    private String birthDate;

    @Column
    private String schoolName;

    @Column
    private String grade;

    @Column(unique = true)
    private String email;

    @CreationTimestamp
    // INSERT 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    // UPDATE 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //@OneToMany(mappedBy = "academyStudent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "lecture_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    //@JsonBackReference
    private List<Lecture> lectures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    @JsonBackReference
    private Academy academy;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // stack overflow
    private List<Bill> bills = new ArrayList<>();
}
