package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Student;
import com.infobasic.ciavarella.nexttechacademy.model.Teacher;
import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.repository.StudentRepository;
import com.infobasic.ciavarella.nexttechacademy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Page<Student> getAllStudentsPaged(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
    }

    public Student getStudentByUserEmail(String email) {
        return studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    /*@Transactional
    public Student createStudent(Student student, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        student.setUser(user);
        return studentRepository.save(student);
    }*/

    @Transactional
    public Student updateStudent(Long studentId, Student studentDetails) {
        Student student = getStudentById(studentId);

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setBirthDate(studentDetails.getBirthDate());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        student.setAddress(studentDetails.getAddress());
        student.setEnrollmentDate(studentDetails.getEnrollmentDate());
        student.setCourse(studentDetails.getCourse());

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    //TODO: getStudentByPhoneNumber; getStudentsByBirthDate; getStudentsByEnrollmentDate
}
