package com.eliceteam8.edupay.academy_management.repository;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyStudentRepository extends JpaRepository<AcademyStudent, Long> {
}
