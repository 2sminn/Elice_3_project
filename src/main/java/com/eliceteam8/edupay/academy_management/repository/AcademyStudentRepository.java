package com.eliceteam8.edupay.academy_management.repository;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademyStudentRepository extends JpaRepository<AcademyStudent, Long> {
    //Optional<AcademyStudent> findByStudentName(String studentName);

    Optional<AcademyStudent> findById(@Param("studentId") Long id);

    Optional<AcademyStudent> findByPhoneNumber(String phoneNumber);
    Optional<AcademyStudent> findByEmail(String email);

    List<AcademyStudent> findByStudentName(String studentName); // 원생관리 > 학생 이름으로 조회
}
