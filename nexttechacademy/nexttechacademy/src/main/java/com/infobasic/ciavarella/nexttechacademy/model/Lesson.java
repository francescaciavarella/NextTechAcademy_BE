package com.infobasic.ciavarella.nexttechacademy.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String argoument;
}
