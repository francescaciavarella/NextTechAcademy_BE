package com.infobasic.ciavarella.nexttechacademy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String description;
    private int cfu;
    private String accademicYear;

    @OneToMany(mappedBy = "course")
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_lesson",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_exam",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private Set<Exam> exams = new HashSet<>();
}
