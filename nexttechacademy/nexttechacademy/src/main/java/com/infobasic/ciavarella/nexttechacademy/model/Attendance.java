package com.infobasic.ciavarella.nexttechacademy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;
    private LocalDate date;
    private boolean presence;

    @OneToOne(mappedBy = "attendance")
    private Lesson lesson;
}
