package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Attendance;
import com.infobasic.ciavarella.nexttechacademy.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Page<Attendance> getAllAttendances(Pageable pageable) {
        return attendanceRepository.findAll(pageable);
    }

    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance con ID " + id + " non trovato"));
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> saveBulkAttendances(List<Attendance> attendances) {
        return attendanceRepository.saveAll(attendances);
    }

    public void deleteAttendanceById(Long id) {
        if (attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Attendance not found with id: " + id);
        }
    }

    public Attendance updateAttendance(Long id, Attendance attendance) {
        Optional<Attendance> existingAttendanceOptional = attendanceRepository.findById(id);
        if (!existingAttendanceOptional.isPresent()) {
            throw new RuntimeException("Attendance not found with id: " + id);
        }
        Attendance existingAttendance = existingAttendanceOptional.get();
        existingAttendance.setLesson(attendance.getLesson());
        existingAttendance.setDate(attendance.getDate());
        existingAttendance.setPresence(attendance.isPresence());
        return attendanceRepository.save(existingAttendance);
    }

    public Page<Attendance> getAttendancesByLessonId(Long lessonId, Pageable pageable) {
        return attendanceRepository.findByLessonId(lessonId, pageable);
    }

    // TODO: getAttendanceRateForStudent;
}
