package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Lesson;
import com.infobasic.ciavarella.nexttechacademy.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Page<Lesson> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson con ID " + id + " non trovato"));
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void deleteLessonById(Long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lesson not found with id: " + id);
        }
    }

    public Lesson updateLesson(Long id, Lesson lesson) {
        Optional<Lesson> existingLessonOptional = lessonRepository.findById(id);
        if (!existingLessonOptional.isPresent()) {
            throw new RuntimeException("Lesson not found with id: " + id);
        }
        Lesson existingLesson = existingLessonOptional.get();
        existingLesson.setStartTime(lesson.getStartTime());
        existingLesson.setEndTime(lesson.getEndTime());
        existingLesson.setClassroom(lesson.getClassroom());
        return lessonRepository.save(existingLesson);
    }

    public Page<Lesson> getLessonsByCourseId(Long courseId, Pageable pageable) {
        return lessonRepository.findByCourseId(courseId, pageable);
    }
}
