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
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroomId;
    private String classroomName;
    private String capacity;
    private String description;

    @OneToMany(mappedBy = "classroom")
    private Set<Lesson> lessons = new HashSet<>();
}
