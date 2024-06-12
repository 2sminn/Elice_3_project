package com.eliceteam8.edupay.academy_management.repository;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.entity.LectureStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByAcademyStudentId(Long studentId);

    Page<Lecture> findAll(Pageable pageable);

    @Query("SELECT s FROM Lecture s WHERE " +
            "(:lectureName IS NULL OR s.lectureName LIKE %:lectureName%) AND " +
            "(:teacherName IS NULL OR s.teacherName LIKE %:teacherName%) AND " +
            "(:lectureStatus IS NULL OR s.lectureStatus LIKE :lectureStatus)")
    List<Lecture> searchLectures(
            @Param("lectureName") String studentName,
            @Param("teacherName") String teacherName,
            @Param("lectureStatus") LectureStatus lectureStatus);


}
