package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Attendance;
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
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByDate(LocalDate date);

    List<Attendance> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Attendance> findByPresence(boolean presence);

    @Query("SELECT a FROM Attendance a WHERE a.lesson.lessonId = :lessonId")
    Page<Attendance> findByLessonId(@Param("lessonId") Long lessonId, Pageable pageable);

    @Query("SELECT a FROM Attendance a WHERE a.lesson.classroom.classroomId = :classroomId")
    List<Attendance> findByClassroomId(@Param("classroomId") Long classroomId);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.presence = true AND a.date BETWEEN :startDate AND :endDate")
    int countPresentAttendancesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.presence = false AND a.date BETWEEN :startDate AND :endDate")
    int countAbsentAttendancesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Page<Attendance> findAll(Pageable pageable);
}
