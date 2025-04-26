package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByClassroomName(String classroomName);

    List<Classroom> findByCapacity(String capacity);

    @Query("SELECT c FROM Classroom c WHERE c.description LIKE %:keyword%")
    List<Classroom> findByDescriptionContaining(@Param("keyword") String keyword);

    @Query("SELECT c FROM Classroom c WHERE CAST(c.capacity AS int) >= :minCapacity")
    List<Classroom> findByCapacityGreaterThanEqual(@Param("minCapacity") int minCapacity);

    @Query("SELECT c FROM Classroom c WHERE c.classroomId NOT IN " +
            "(SELECT l.classroom.classroomId FROM Lesson l WHERE l.date = :date AND " +
            "((l.startTime <= :endTime AND l.endTime >= :startTime)))")
    List<Classroom> findAvailableClassrooms(@Param("date") LocalDate date,
                                            @Param("startTime") LocalTime startTime,
                                            @Param("endTime") LocalTime endTime);

    @Query("SELECT COUNT(l) FROM Classroom c JOIN c.lessons l WHERE c.classroomId = :classroomId")
    int countLessonsByClassroomId(@Param("classroomId") Long classroomId);

    Page<Classroom> findAll(Pageable pageable);
}
