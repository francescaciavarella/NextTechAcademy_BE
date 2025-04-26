package com.infobasic.ciavarella.nexttechacademy.model;

import com.infobasic.ciavarella.nexttechacademy.spring_jwt.model.AuthUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private LocalDate enrollmentDate;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "student")
    private List<ExamResult> examResults;

    @OneToOne(mappedBy = "student")
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "student")
    private Set<Payment> payments = new HashSet<>();

    @OneToOne
    private AuthUser authUser;

    // Getters and setters
    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }
}