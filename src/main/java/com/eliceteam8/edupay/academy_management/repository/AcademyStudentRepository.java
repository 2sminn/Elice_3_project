package com.eliceteam8.edupay.academy_management.repository;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademyStudentRepository extends JpaRepository<AcademyStudent, Long> {
    Optional<AcademyStudent> findByStudentName(String studentName);
}
