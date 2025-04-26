package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Exam;
import com.infobasic.ciavarella.nexttechacademy.model.ExamResult;
import com.infobasic.ciavarella.nexttechacademy.model.Student;
import com.infobasic.ciavarella.nexttechacademy.repository.ExamRepository;
import com.infobasic.ciavarella.nexttechacademy.repository.ExamResultRepository;
import com.infobasic.ciavarella.nexttechacademy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamResultService {
    private final ExamResultRepository examResultRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    @Autowired
    public ExamResultService(ExamResultRepository examResultRepository,
                             StudentRepository studentRepository,
                             ExamRepository examRepository) {
        this.examResultRepository = examResultRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    public List<ExamResult> getAllExamResults() {
        return examResultRepository.findAll();
    }

    public Page<ExamResult> getAllExamResultsPaged(Pageable pageable) {
        return examResultRepository.findAll(pageable);
    }

    public ExamResult getExamResultById(Long id) {
        return examResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam result not found with id: " + id));
    }

    @Transactional
    public ExamResult createExamResult(ExamResult examResult, Long studentId, Long examId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + examId));

        examResult.setStudent(student);
        examResult.setExam(exam);

        return examResultRepository.save(examResult);
    }

    @Transactional
    public ExamResult updateExamResult(Long id, ExamResult examResultDetails) {
        ExamResult examResult = getExamResultById(id);

        return examResultRepository.save(examResult);
    }

    @Transactional
    public void deleteExamResult(Long id) {
        if (!examResultRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exam result not found with id: " + id);
        }
        examResultRepository.deleteById(id);
    }

    public List<ExamResult> getExamResultsByStudentId(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    public List<ExamResult> getExamResultsByExamId(Long examId) {
        return examResultRepository.findByExamId(examId);
    }

    public List<ExamResult> getExamResultsByStudentIdAndExamId(Long studentId, Long examId) {
        return examResultRepository.findByExamIdAndStudentId(examId, studentId);
    }

    public int getExamResultCountByExamId(Long examId) {
        return examResultRepository.countByExamId(examId);
    }

    public int getExamResultCountByStudentId(Long studentId) {
        return examResultRepository.countByStudentId(studentId);
    }

    //TODO: deleteExamResult; getPassedExams; getFailedExams; getAvarageScore; getAvarageScoreByExam
}
