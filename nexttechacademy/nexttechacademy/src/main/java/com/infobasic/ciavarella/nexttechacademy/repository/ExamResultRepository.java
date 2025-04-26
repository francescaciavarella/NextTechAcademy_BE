package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.ExamResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    @Query("SELECT er FROM ExamResult er WHERE er.student.studentId = :studentId")
    List<ExamResult> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT er FROM ExamResult er WHERE er.exam.examId = :examId")
    List<ExamResult> findByExamId(@Param("examId") Long examId);

    @Query("SELECT er FROM ExamResult er WHERE er.student.studentId = :studentId AND er.exam.examId = :examId")
    List<ExamResult> findByExamIdAndStudentId(@Param("examId") Long examId, @Param("studentId") Long studentId);

    @Query("SELECT COUNT(er) FROM ExamResult er WHERE er.exam.examId = :examId")
    int countByExamId(@Param("examId") Long examId);

    @Query("SELECT COUNT(er) FROM ExamResult er WHERE er.student.studentId = :studentId")
    int countByStudentId(@Param("studentId") Long studentId);

    Page<ExamResult> findAll(Pageable pageable);
}
