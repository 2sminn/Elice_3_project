package com.eliceteam8.edupay.academy_management.repository;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface AcademyStudentRepository extends JpaRepository<AcademyStudent, Long> {
    Optional<AcademyStudent> findByStudentName(String studentName);

    Optional<AcademyStudent> findById(@Param("studentId") Long id);

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

    /*@Query("SELECT s FROM AcademyStudent S WHERE" +
            "(s.studentName = :studentName OR : studentName IS NULL) AND " +
            "(s.phoneNumber = :phoneNumber or :phoneNumber IS NULL) AND " +
            "(s.email = :email OR : email IS NULL)")*/
    @Query("SELECT s FROM AcademyStudent s WHERE " +
            "(:studentName IS NULL OR s.studentName LIKE %:studentName%) AND " +
            "(:phoneNumber IS NULL OR s.phoneNumber LIKE %:phoneNumber%) AND " +
            "(:email IS NULL OR s.email LIKE %:email%)")
    List<AcademyStudent> searchStudents(
            @Param("studentName") String studentName,
            @Param("phoneNumber") String phoneNumber,
            @Param("email") String email);

}
