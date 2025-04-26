package com.infobasic.ciavarella.nexttechacademy.util;

import com.infobasic.ciavarella.nexttechacademy.model.Student;
import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.repository.UserRepository;
import com.infobasic.ciavarella.nexttechacademy.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(4)
@Slf4j
public class StudentDataLoader implements CommandLineRunner {

    private final StudentService studentService;
    private final UserRepository userRepository;

    public StudentDataLoader(StudentService studentService, UserRepository userRepository) {
        this.studentService = studentService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Inserimento studenti di test nel database...");

        User studentUser1 = userRepository.findByEmail("student1@example.com")
                .orElseThrow(() -> new RuntimeException("Student user 1 not found"));

        User studentUser2 = userRepository.findByEmail("student2@example.com")
                .orElseThrow(() -> new RuntimeException("Student user 2 not found"));

        User studentUser3 = userRepository.findByEmail("student3@example.com")
                .orElseThrow(() -> new RuntimeException("Student user 3 not found"));

        Student student1 = new Student();
        student1.setFirstName("Giulia");
        student1.setLastName("Neri");
        student1.setBirthDate(LocalDate.of(2000, 5, 15));
        student1.setEnrollmentDate(LocalDate.of(2020, 9, 1));

        Student student2 = new Student();
        student2.setFirstName("Luca");
        student2.setLastName("Gialli");
        student2.setBirthDate(LocalDate.of(2001, 8, 20));
        student2.setEnrollmentDate(LocalDate.of(2020, 9, 1));

        Student student3 = new Student();
        student3.setFirstName("Sofia");
        student3.setLastName("Blu");
        student3.setBirthDate(LocalDate.of(1999, 3, 10));
        student3.setEnrollmentDate(LocalDate.of(2019, 9, 1));

        studentService.createStudent(student1, studentUser1.getStudent().getStudentId());
        studentService.createStudent(student2, studentUser2.getStudent().getStudentId());
        studentService.createStudent(student3, studentUser3.getStudent().getStudentId());

        log.info("Studenti di test inseriti con successo nel database.");
    }
}
