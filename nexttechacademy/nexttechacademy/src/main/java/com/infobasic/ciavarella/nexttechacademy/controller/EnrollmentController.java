package com.infobasic.ciavarella.nexttechacademy.controller;

import com.infobasic.ciavarella.nexttechacademy.model.Enrollment;
import com.infobasic.ciavarella.nexttechacademy.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
}
