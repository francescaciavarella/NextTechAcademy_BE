package com.infobasic.ciavarella.nexttechacademy.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;
    private LocalDate examDate;
    private String type;
    private int maxScore;

    @OneToMany(mappedBy = "exam")
    private List<ExamResult> results;
}
