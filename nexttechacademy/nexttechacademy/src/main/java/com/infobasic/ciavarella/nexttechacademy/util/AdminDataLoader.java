package com.infobasic.ciavarella.nexttechacademy.util;

import com.infobasic.ciavarella.nexttechacademy.model.Admin;
import com.infobasic.ciavarella.nexttechacademy.model.User;
import com.infobasic.ciavarella.nexttechacademy.repository.UserRepository;
import com.infobasic.ciavarella.nexttechacademy.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(2)
@Slf4j
public class AdminDataLoader implements CommandLineRunner {

    private final AdminService adminService;
    private final UserRepository userRepository;

    public AdminDataLoader(AdminService adminService, UserRepository userRepository) {
        this.adminService = adminService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Inserimento admin di test nel database...");

        User adminUser = userRepository.findByEmail("admin@example.com")
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        Admin admin = new Admin();
        admin.setFirstName("Mario");
        admin.setLastName("Rossi");
        admin.setBirthDate(LocalDate.of(1980, 5, 15));

        adminService.createAdmin(admin, admin.getAdminId());

        log.info("Admin di test inserito con successo nel database.");
    }
}

