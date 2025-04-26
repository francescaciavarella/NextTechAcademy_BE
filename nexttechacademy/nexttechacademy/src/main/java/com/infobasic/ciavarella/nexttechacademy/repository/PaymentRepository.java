package com.infobasic.ciavarella.nexttechacademy.repository;

import com.infobasic.ciavarella.nexttechacademy.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentDate(LocalDate paymentDate);

    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);

    List<Payment> findByAmount(int amount);

    List<Payment> findByPaymentMethod(String paymentMethod);

    @Query("SELECT p FROM Payment p WHERE p.description LIKE %:keyword%")
    List<Payment> findByDescriptionContaining(@Param("keyword") String keyword);

    @Query("SELECT p FROM Payment p WHERE p.student.studentId = :studentId")
    Page<Payment> findByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.student.studentId = :studentId")
    Integer sumAmountByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    Integer sumAmountByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT p FROM Payment p WHERE p.amount > :minAmount")
    List<Payment> findByAmountGreaterThan(@Param("minAmount") int minAmount);

    Page<Payment> findAll(Pageable pageable);
}
