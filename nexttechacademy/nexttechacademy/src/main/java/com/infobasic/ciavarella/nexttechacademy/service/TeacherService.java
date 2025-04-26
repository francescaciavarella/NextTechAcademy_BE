package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Teacher;
import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.repository.TeacherRepository;
import com.infobasic.ciavarella.nexttechacademy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Page<Teacher> getAllTeachersPaged(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
    }

    public Teacher getTeacherByUserEmail(String email) {
        return teacherRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with email: " + email));
    }

    @Transactional
    public Teacher createTeacher(Teacher teacher, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        teacher.setUser(user);
        return teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher updateTeacher(Long teacherId, Teacher teacherDetails) {
        Teacher teacher = getTeacherById(teacherId);

        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setBirthDate(teacherDetails.getBirthDate());
        teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
        teacher.setAddress(teacherDetails.getAddress());

        return teacherRepository.save(teacher);
    }

    @Transactional
    public void deleteTeacher(Long teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new ResourceNotFoundException("Teacher not found with id: " + teacherId);
        }
        teacherRepository.deleteById(teacherId);
    }

    public List<Teacher> getTeachersByFirstName(String firstName) {
        return teacherRepository.findByFirstName(firstName);
    }

    public List<Teacher> getTeachersByLastName(String lastName) {
        return teacherRepository.findByLastName(lastName);
    }

    public List<Teacher> getTeachersByCourseId(Long courseId) {
        return teacherRepository.findByCourseId(courseId);
    }

    //TODO: getStudentByPhoneNumber; getStudentsByBirthDate; getStudentsByEnrollmentDate
}
