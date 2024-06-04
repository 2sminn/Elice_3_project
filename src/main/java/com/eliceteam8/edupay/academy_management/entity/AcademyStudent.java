package com.eliceteam8.edupay.academy_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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

    @Column
    private String phoneNumber;

    @Column
    private String birthDate;

    @Column
    private String schoolName;

    @Column
    private String grade;

    @Column
    private String email;

    @CreationTimestamp
    // INSERT 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    // UPDATE 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "academy_id")
    //private Academy academy;

    @OneToMany(mappedBy = "academy_student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy;

}
