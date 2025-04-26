package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByDate(LocalDate date);

    List<Lesson> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT l FROM Lesson l WHERE l.date = :date AND l.startTime >= :startTime AND l.endTime <= :endTime")
    List<Lesson> findByDateAndTimeRange(@Param("date") LocalDate date,
                                        @Param("startTime") LocalTime startTime,
                                        @Param("endTime") LocalTime endTime);

    @Query("SELECT l FROM Lesson l WHERE l.classroom.classroomId = :classroomId")
    List<Lesson> findByClassroomId(@Param("classroomId") Long classroomId);

    @Query("SELECT l FROM Lesson l JOIN l.courses c WHERE c.courseId = :courseId")
    Page<Lesson> findByCourseId(@Param("courseId") Long courseId, Pageable pageable);

    @Query("SELECT l FROM Lesson l WHERE l.argoument LIKE %:keyword%")
    List<Lesson> findByArgoumentContaining(@Param("keyword") String keyword);

    @Query("SELECT l FROM Lesson l WHERE l.date >= CURRENT_DATE ORDER BY l.date ASC, l.startTime ASC")
    List<Lesson> findUpcomingLessons(Pageable pageable);

    @Query("SELECT l FROM Lesson l WHERE l.classroom.classroomId = :classroomId AND l.date = :date")
    List<Lesson> findByClassroomIdAndDate(@Param("classroomId") Long classroomId, @Param("date") LocalDate date);

    Page<Lesson> findAll(Pageable pageable);
}
