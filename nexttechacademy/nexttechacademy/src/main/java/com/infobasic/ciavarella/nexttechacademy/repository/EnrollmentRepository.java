package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT e FROM Enrollment e WHERE e.student.studentId = :studentId")
    Optional<Enrollment> findByStudentId(@Param("studentId") Long studentId);

    List<Enrollment> findByEnrollmentDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.enrollmentDate = :date")
    int countByEnrollmentDate(@Param("date") LocalDate date);

    @Query("SELECT e FROM Enrollment e WHERE e.student.course.courseId = :courseId")
    List<Enrollment> findByCourseId(@Param("courseId") Long courseId);

    Page<Enrollment> findAll(Pageable pageable);
}
