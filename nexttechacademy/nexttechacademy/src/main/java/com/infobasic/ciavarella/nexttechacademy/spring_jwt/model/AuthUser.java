package com.infobasic.ciavarella.nexttechacademy.spring_jwt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.infobasic.ciavarella.nexttechacademy.model.Admin;
import com.infobasic.ciavarella.nexttechacademy.model.Student;
import com.infobasic.ciavarella.nexttechacademy.model.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JsonManagedReference(value = "student-authUser")
  private Student student;

  @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JsonManagedReference(value = "teacher-authUser")
  private Teacher teacher;

  @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JsonManagedReference(value = "admin-authUser")
  private Admin admin;

  @NotNull
  @Column(name = "registration_date") // Explicitly specify the column name
  private LocalDate registrationDate = LocalDate.now();

  // Constructor with username, email, password
  public AuthUser(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  // Constructor with email, password, role
  public AuthUser(String email, String password, Role role) {
    // Generate a username from email if not provided
    this.username = email.split("@")[0];
    this.email = email;
    this.password = password;
    this.roles.add(role);
  }

  // Constructor with email and password
  public AuthUser(String email, String password) {
    this.username = email.split("@")[0];
    this.email = email;
    this.password = password;
  }
}
