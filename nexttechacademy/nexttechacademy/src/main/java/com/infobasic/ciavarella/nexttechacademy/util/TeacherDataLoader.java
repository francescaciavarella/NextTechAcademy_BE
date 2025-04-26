package com.infobasic.ciavarella.nexttechacademy.util;

import com.infobasic.ciavarella.nexttechacademy.model.Teacher;
import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.repository.UserRepository;
import com.infobasic.ciavarella.nexttechacademy.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(3)
@Slf4j
public class TeacherDataLoader implements CommandLineRunner {

    private final TeacherService teacherService;
    private final UserRepository userRepository;

    public TeacherDataLoader(TeacherService teacherService, UserRepository userRepository) {
        this.teacherService = teacherService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Inserimento insegnanti di test nel database...");

        User teacherUser1 = userRepository.findByEmail("teacher1@example.com")
                .orElseThrow(() -> new RuntimeException("Teacher user 1 not found"));

        User teacherUser2 = userRepository.findByEmail("teacher2@example.com")
                .orElseThrow(() -> new RuntimeException("Teacher user 2 not found"));

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Laura");
        teacher1.setLastName("Bianchi");
        teacher1.setBirthDate(LocalDate.of(1975, 3, 10));

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Marco");
        teacher2.setLastName("Verdi");
        teacher2.setBirthDate(LocalDate.of(1982, 7, 22));

        teacherService.createTeacher(teacher1, teacherUser1.getTeacher().getTeacherId());
        teacherService.createTeacher(teacher2, teacherUser2.getTeacher().getTeacherId());

        log.info("Insegnanti di test inseriti con successo nel database.");
    }
}
