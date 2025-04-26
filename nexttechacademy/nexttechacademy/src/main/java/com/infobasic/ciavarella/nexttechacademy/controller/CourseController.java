package com.infobasic.ciavarella.nexttechacademy.controller;

import com.infobasic.ciavarella.nexttechacademy.model.Course;
import com.infobasic.ciavarella.nexttechacademy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Course>> getAllCoursesPaged(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCoursesPaged(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/name/{courseName}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String courseName) {
        return ResponseEntity.ok(courseService.getCourseByName(courseName));
    }

    @GetMapping("/cfu/{cfu}")
    public ResponseEntity<List<Course>> getCoursesByCfu(@PathVariable int cfu) {
        return ResponseEntity.ok(courseService.getCoursesByCfu(cfu));
    }

    @GetMapping("/academic-year/{accademicYear}")
    public ResponseEntity<List<Course>> getCoursesByAccademicYear(@PathVariable String accademicYear) {
        return ResponseEntity.ok(courseService.getCoursesByAccademicYear(accademicYear));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> getCoursesByDescriptionKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(courseService.getCoursesByDescriptionKeyword(keyword));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}