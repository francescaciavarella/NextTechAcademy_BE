package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Admin;
import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.repository.AdminRepository;
import com.infobasic.ciavarella.nexttechacademy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + adminId));
    }

    public Admin getAdminByUserEmail(String email) {
        return adminRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with email: " + email));
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    /*@Transactional
    public Admin createAdmin(Admin admin, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        admin.setUser(user);
        return adminRepository.save(admin);
    }*/

    @Transactional
    public Admin updateAdmin(Long adminId, Admin adminDetails) {
        Admin admin = getAdminById(adminId);

        admin.setFirstName(adminDetails.getFirstName());
        admin.setLastName(adminDetails.getLastName());
        admin.setBirthDate(adminDetails.getBirthDate());

        return adminRepository.save(admin);
    }

    @Transactional
    public void deleteAdmin(Long adminId) {
        if (!adminRepository.existsById(adminId)) {
            throw new ResourceNotFoundException("Admin not found with id: " + adminId);
        }
        adminRepository.deleteById(adminId);
    }

    public List<Admin> getAdminsByName(String firstName, String lastName) {
        return adminRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Admin> getAdminsByBirthDateBefore(LocalDate date) {
        return adminRepository.findByBirthDateBefore(date);
    }

    public Admin getAdminByUserId(Long userId) {
        return adminRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found for user with id: " + userId));
    }
}
