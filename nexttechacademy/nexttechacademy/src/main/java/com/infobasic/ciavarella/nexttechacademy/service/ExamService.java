package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Exam;
import com.infobasic.ciavarella.nexttechacademy.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Page<Exam> getAllExamsPaged(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + id));
    }

    @Transactional
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Transactional
    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = getExamById(id);

        exam.setExamDate(examDetails.getExamDate());
        exam.setType(examDetails.getType());
        exam.setMaxScore(examDetails.getMaxScore());

        return examRepository.save(exam);
    }

    @Transactional
    public void deleteExam(Long id) {
        if (!examRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exam not found with id: " + id);
        }
        examRepository.deleteById(id);
    }

    public List<Exam> getExamsByDate(LocalDate examDate) {
        return examRepository.findByExamDate(examDate);
    }

    public List<Exam> getExamsByDateRange(LocalDate startDate, LocalDate endDate) {
        return examRepository.findByExamDateBetween(startDate, endDate);
    }

    public List<Exam> getExamsByType(String type) {
        return examRepository.findByType(type);
    }

    public List<Exam> getExamsByCourseId(Long courseId) {
        return examRepository.findByCourseId(courseId);
    }

    public int getResultCountByExamId(Long examId) {
        return examRepository.countResultsByExamId(examId);
    }

    public List<Exam> getUpcomingExams(Pageable pageable) {
        return examRepository.findUpcomingExams(pageable);
    }
}