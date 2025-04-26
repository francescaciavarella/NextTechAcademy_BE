package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT a FROM Admin a WHERE a.user.email = :email")
    Optional<Admin> findByUserEmail(@Param("email") String email);

    List<Admin> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT a FROM Admin a WHERE a.birthDate < :date")
    List<Admin> findByBirthDateBefore(@Param("date") LocalDate date);

    @Query("SELECT a FROM Admin a WHERE a.user.userId = :userId")
    Optional<Admin> findByUserId(@Param("userId") Long userId);
}
