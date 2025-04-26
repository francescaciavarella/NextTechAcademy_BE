package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Enrollment;
import com.infobasic.ciavarella.nexttechacademy.model.Student;
import com.infobasic.ciavarella.nexttechacademy.repository.EnrollmentRepository;
import com.infobasic.ciavarella.nexttechacademy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Page<Enrollment> getAllEnrollmentsPaged(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
    }

    public Enrollment getEnrollmentByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for student with id: " + studentId));
    }

    @Transactional
    public Enrollment createEnrollment(Enrollment enrollment, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        enrollment.setStudent(student);
        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment updateEnrollment(Long id, Enrollment enrollmentDetails) {
        Enrollment enrollment = getEnrollmentById(id);

        enrollment.setEnrollmentDate(enrollmentDetails.getEnrollmentDate());

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> getEnrollmentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return enrollmentRepository.findByEnrollmentDateBetween(startDate, endDate);
    }

    public int getEnrollmentCountByDate(LocalDate date) {
        return enrollmentRepository.countByEnrollmentDate(date);
    }

    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}