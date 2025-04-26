package com.infobasic.ciavarella.nexttechacademy.util;

import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class UserDataLoader implements CommandLineRunner {

    private final UserService userService;

    public UserDataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Inserimento utenti di test nel database...");

        // Admin
        User adminUser = new User();
        adminUser.setEmail("admin@example.com");
        adminUser.setPassword("admin123");
        adminUser.setRole("ADMIN");

        // Teacher
        User teacherUser1 = new User();
        teacherUser1.setEmail("teacher1@example.com");
        teacherUser1.setPassword("teacher123");
        teacherUser1.setRole("TEACHER");

        User teacherUser2 = new User();
        teacherUser2.setEmail("teacher2@example.com");
        teacherUser2.setPassword("teacher456");
        teacherUser2.setRole("TEACHER");

        // Student
        User studentUser1 = new User();
        studentUser1.setEmail("student1@example.com");
        studentUser1.setPassword("student123");
        studentUser1.setRole("STUDENT");

        User studentUser2 = new User();
        studentUser2.setEmail("student2@example.com");
        studentUser2.setPassword("student456");
        studentUser2.setRole("STUDENT");

        User studentUser3 = new User();
        studentUser3.setEmail("student3@example.com");
        studentUser3.setPassword("student789");
        studentUser3.setRole("STUDENT");

        userService.createUser(adminUser);
        userService.createUser(teacherUser1);
        userService.createUser(teacherUser2);
        userService.createUser(studentUser1);
        userService.createUser(studentUser2);
        userService.createUser(studentUser3);

        log.info("Test users Utenti di test inseriti con successo nel database.");
    }
}
