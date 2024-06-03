package com.eliceteam8.edupay.academy_management.repository;

import com.eliceteam8.edupay.academy_management.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
