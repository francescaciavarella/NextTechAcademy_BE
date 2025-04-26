package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.user.email = :email")
    Optional<Student> findByUserEmail(@Param("email") String email);

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    @Query("SELECT s FROM Student s WHERE s.course.courseId = :courseId")
    List<Student> findByCourseId(@Param("courseId") Long courseId);

    Page<Student> findAll(Pageable pageable);

    // TODO: findByPhoneNumber; findByAddress; findByBirthDate, findByEnrollmentDate
}
