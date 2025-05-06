package com.infobasic.ciavarella.nexttechacademy.controller;

import com.infobasic.ciavarella.nexttechacademy.model.Student;
import com.infobasic.ciavarella.nexttechacademy.model.Teacher;
import com.infobasic.ciavarella.nexttechacademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Student>> getAllStudentsPaged(Pageable pageable) {
        Page<Student> students = studentService.getAllStudentsPaged(pageable);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Student student = studentService.getStudentByUserEmail(email);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}")
    public ResponseEntity<Student> saveStudent(
            @RequestBody Student student,
            @RequestParam Long userId) {
        Student createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<Student>> getStudentsByFirstName(@PathVariable String firstName) {
        List<Student> students = studentService.getStudentsByFirstName(firstName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<Student>> getStudentsByLastName(@PathVariable String lastName) {
        List<Student> students = studentService.getStudentsByLastName(lastName);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long courseId) {
        List<Student> students = studentService.getStudentsByCourseId(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}