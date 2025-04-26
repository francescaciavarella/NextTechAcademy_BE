package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByExamDate(LocalDate examDate);

    List<Exam> findByExamDateBetween(LocalDate startDate, LocalDate endDate);

    List<Exam> findByType(String type);

    @Query("SELECT e FROM Exam e JOIN e.courses c WHERE c.courseId = :courseId")
    List<Exam> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT e FROM Exam e WHERE e.maxScore >= :minScore")
    List<Exam> findByMaxScoreGreaterThanEqual(@Param("minScore") int minScore);

    @Query("SELECT COUNT(r) FROM Exam e JOIN e.results r WHERE e.examId = :examId")
    int countResultsByExamId(@Param("examId") Long examId);

    Page<Exam> findAll(Pageable pageable);

    @Query("SELECT e FROM Exam e WHERE e.examDate >= CURRENT_DATE ORDER BY e.examDate ASC")
    List<Exam> findUpcomingExams(Pageable pageable);
}
