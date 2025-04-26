package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findByRole(@Param("role") String role);

    @Query("SELECT u FROM User u WHERE u.student IS NOT NULL")
    List<User> findAllStudentUsers();

    @Query("SELECT u FROM User u WHERE u.teacher IS NOT NULL")
    List<User> findAllTeacherUsers();

    @Query("SELECT u FROM User u WHERE u.admin IS NOT NULL")
    List<User> findAllAdminUsers();
}
