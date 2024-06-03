package com.eliceteam8.edupay.academy_management.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'OPEN'")
    private LectureStatus lectureStatus; // 강의 상태

}

