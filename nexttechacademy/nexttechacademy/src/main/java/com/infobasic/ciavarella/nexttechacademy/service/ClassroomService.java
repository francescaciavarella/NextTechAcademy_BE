package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Classroom;
import com.infobasic.ciavarella.nexttechacademy.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Page<Classroom> getAllClassroomsPaged(Pageable pageable) {
        return classroomRepository.findAll(pageable);
    }

    public Classroom getClassroomById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id: " + id));
    }

    public Classroom getClassroomByName(String classroomName) {
        return classroomRepository.findByClassroomName(classroomName)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with name: " + classroomName));
    }

    @Transactional
    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Transactional
    public Classroom updateClassroom(Long id, Classroom classroomDetails) {
        Classroom classroom = getClassroomById(id);

        classroom.setClassroomName(classroomDetails.getClassroomName());
        classroom.setCapacity(classroomDetails.getCapacity());
        classroom.setDescription(classroomDetails.getDescription());

        return classroomRepository.save(classroom);
    }

    @Transactional
    public void deleteClassroom(Long id) {
        if (!classroomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Classroom not found with id: " + id);
        }
        classroomRepository.deleteById(id);
    }

    public List<Classroom> getClassroomsByCapacity(String capacity) {
        return classroomRepository.findByCapacity(capacity);
    }

    public List<Classroom> getClassroomsByDescriptionKeyword(String keyword) {
        return classroomRepository.findByDescriptionContaining(keyword);
    }

    public List<Classroom> getClassroomsByMinCapacity(int minCapacity) {
        return classroomRepository.findByCapacityGreaterThanEqual(minCapacity);
    }

    public List<Classroom> getAvailableClassrooms(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return classroomRepository.findAvailableClassrooms(date, startTime, endTime);
    }

    public int getLessonCountByClassroomId(Long classroomId) {
        return classroomRepository.countLessonsByClassroomId(classroomId);
    }
}
