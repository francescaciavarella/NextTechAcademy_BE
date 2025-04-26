package com.infobasic.ciavarella.nexttechacademy.util;

import com.infobasic.ciavarella.nexttechacademy.model.Course;
import com.infobasic.ciavarella.nexttechacademy.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@Slf4j
public class CourseDataLoader implements CommandLineRunner {

    private final CourseService courseService;

    public CourseDataLoader(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Inserimento corsi di test nel database...");

        Course mathCourse = new Course();
        mathCourse.setCourseName("Matematica Avanzata");
        mathCourse.setDescription("Corso di matematica avanzata con calcolo differenziale e integrale");
        mathCourse.setAccademicYear("2023/2024");
        mathCourse.setCfu(12);

        Course csCourse = new Course();
        csCourse.setCourseName("Programmazione Java");
        csCourse.setDescription("Corso di programmazione avanzata in Java");
        csCourse.setAccademicYear("2024/2025");
        csCourse.setCfu(10);

        Course dbCourse = new Course();
        dbCourse.setCourseName("Database SQL");
        dbCourse.setDescription("Corso sui database relazionali e SQL");
        dbCourse.setAccademicYear("2024/2025");
        dbCourse.setCfu(8);

        courseService.createCourse(mathCourse);
        courseService.createCourse(csCourse);
        courseService.createCourse(dbCourse);

        log.info("Corsi di test inseriti con successo nel database.");
    }
}
