package com.infobasic.ciavarella.nexttechacademy.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;
    private LocalDate enrollmentDate;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Student student;
}