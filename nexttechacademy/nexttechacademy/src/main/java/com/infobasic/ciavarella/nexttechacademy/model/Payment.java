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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private LocalDate paymentDate;
    private int amount;
    private String paymentMetod;
    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
