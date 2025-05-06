package com.infobasic.ciavarella.nexttechacademy.controller;

import com.infobasic.ciavarella.nexttechacademy.model.Admin;
import com.infobasic.ciavarella.nexttechacademy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> getAdminByUserEmail(@PathVariable String email) {
        Admin admin = adminService.getAdminByUserEmail(email);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Admin> getAdminByUserId(@PathVariable Long userId) {
        Admin admin = adminService.getAdminByUserId(userId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/admin/{adminId}")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.saveAdmin(admin);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Admin updatedAdmin = adminService.updateAdmin(id, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Admin>> getAdminsByName(
            @RequestParam String firstName, @RequestParam String lastName) {
        List<Admin> admins = adminService.getAdminsByName(firstName, lastName);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/birthDateBefore")
    public ResponseEntity<List<Admin>> getAdminsByBirthDateBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Admin> admins = adminService.getAdminsByBirthDateBefore(date);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}