package com.infobasic.ciavarella.nexttechacademy.repository;


import com.infobasic.ciavarella.nexttechacademy.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseName(String courseName);

    List<Course> findByCfu(int cfu);

    List<Course> findByAccademicYear(String accademicYear);

    @Query("SELECT c FROM Course c WHERE c.description LIKE %:keyword%")
    List<Course> findByDescriptionContaining(@Param("keyword") String keyword);

    @Query("SELECT c FROM Course c JOIN c.teachers t WHERE t.teacherId = :teacherId")
    List<Course> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.studentId = :studentId")
    List<Course> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT c FROM Course c JOIN c.exams e WHERE e.examId = :examId")
    List<Course> findByExamId(@Param("examId") Long examId);

    @Query("SELECT c FROM Course c JOIN c.lessons l WHERE l.lessonId = :lessonId")
    List<Course> findByLessonId(@Param("lessonId") Long lessonId);

    @Query("SELECT COUNT(s) FROM Course c JOIN c.students s WHERE c.courseId = :courseId")
    int countStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT COUNT(e) FROM Course c JOIN c.exams e WHERE c.courseId = :courseId")
    int countExamsByCourseId(@Param("courseId") Long courseId);

    Page<Course> findAll(Pageable pageable);
}
