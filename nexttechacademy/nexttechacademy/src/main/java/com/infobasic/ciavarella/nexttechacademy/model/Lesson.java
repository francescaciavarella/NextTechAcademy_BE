package com.infobasic.ciavarella.nexttechacademy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String argoument;

    @ManyToMany(mappedBy = "lessons")
    private Set<Course> courses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToOne
    private Attendance attendance;
}
