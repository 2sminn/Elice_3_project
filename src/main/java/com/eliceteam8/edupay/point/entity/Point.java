package com.eliceteam8.edupay.point.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long point;

    @Builder
    public Point(Long userId, Long point) {
        this.userId = userId;
        this.point = point;
    }

    public void addPoint(Long pointToAdd) {
        this.point += pointToAdd;
    }
}
