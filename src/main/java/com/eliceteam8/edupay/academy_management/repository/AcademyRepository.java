package com.eliceteam8.edupay.academy_management.repository;

import com.eliceteam8.edupay.academy_management.dto.response.AcademyCountDTO;
import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AcademyRepository extends JpaRepository<Academy,Long> {


    @Query("SELECT new com.eliceteam8.edupay.academy_management.dto.response.AcademyCountDTO(a.id, count(distinct s.id), count(distinct l.id))  " +
            " FROM Academy a " +
            " LEFT JOIN  a.academyStudents s " +
            " LEFT JOIN a.lectures l " +
            " WHERE a.id = :academyId")
    AcademyCountDTO countStudentAndLecture(@Param("academyId") Long academyId );


    @Query("SELECT sum(b.totalPrice) " +
            " FROM  Academy a " +
            " JOIN  a.bill b  " +
            " WHERE a.id = :academyId AND b.status = 'PAID' ")
    Long sumPaidBill(@Param("academyId") Long academyId);

    Optional<Academy> findById(Long id);

}
