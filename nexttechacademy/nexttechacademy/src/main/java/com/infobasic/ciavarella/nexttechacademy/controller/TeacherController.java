package com.infobasic.ciavarella.nexttechacademy.controller;

import com.infobasic.ciavarella.nexttechacademy.model.Student;
import com.infobasic.ciavarella.nexttechacademy.model.Teacher;
import com.infobasic.ciavarella.nexttechacademy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Teacher>> getAllTeachersPaged(Pageable pageable) {
        Page<Teacher> teachers = teacherService.getAllTeachersPaged(pageable);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Teacher> getTeacherByUserEmail(@PathVariable String email) {
        Teacher teacher = teacherService.getTeacherByUserEmail(email);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping("/teacher/{teacherId}")
    public ResponseEntity<Teacher> saveTeacher(
            @RequestBody Teacher teacher,
            @RequestParam Long userId) {
        Teacher createdTeacher = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<Teacher>> getTeachersByFirstName(@PathVariable String firstName) {
        List<Teacher> teachers = teacherService.getTeachersByFirstName(firstName);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<Teacher>> getTeachersByLastName(@PathVariable String lastName) {
        List<Teacher> teachers = teacherService.getTeachersByLastName(lastName);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Teacher>> getTeachersByCourseId(@PathVariable Long courseId) {
        List<Teacher> students = teacherService.getTeachersByCourseId(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}