package com.eliceteam8.edupay.academy_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_name")
    private String lectureName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "teacher_name")
    private String teacherName;

    @CreationTimestamp
    // INSERT 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    // UPDATE 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    @JsonBackReference
    private Academy academy;

    @ManyToMany(mappedBy = "lectures")
    //@JoinColumn(name = "student_id")
    @JsonBackReference
    private List<AcademyStudent> academyStudent;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'OPEN'")
    private LectureStatus lectureStatus; // 강의 상태

    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureSchedule> lectureSchedules; // 강의 일정 리스트
}

