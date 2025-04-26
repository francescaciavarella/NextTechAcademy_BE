package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE t.user.email = :email")
    Optional<Teacher> findByUserEmail(@Param("email") String email);

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByLastName(String lastName);

    @Query("SELECT t FROM Teacher t JOIN t.courses c WHERE c.courseId = :courseId")
    List<Teacher> findByCourseId(@Param("courseId") Long courseId);

    Page<Teacher> findAll(Pageable pageable);

    // TODO: findByPhoneNumber; findByAddress; findByBirthDate, findByEnrollmentDate
}
