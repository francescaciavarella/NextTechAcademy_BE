package com.infobasic.ciavarella.nexttechacademy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;
    private LocalDate examDate;
    private String type;
    private int maxScore;

    @OneToMany(mappedBy = "exam")
    private List<ExamResult> results;

    @ManyToMany(mappedBy = "exams")
    private Set<Course> courses = new HashSet<>();
}
