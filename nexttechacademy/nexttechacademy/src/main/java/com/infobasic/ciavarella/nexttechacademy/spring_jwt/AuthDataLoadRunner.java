package com.infobasic.ciavarella.nexttechacademy.spring_jwt;

import com.infobasic.ciavarella.nexttechacademy.spring_jwt.model.AuthUser;
import com.infobasic.ciavarella.nexttechacademy.spring_jwt.model.ERole;
import com.infobasic.ciavarella.nexttechacademy.spring_jwt.model.Role;
import com.infobasic.ciavarella.nexttechacademy.spring_jwt.repository.AuthRoleRepository;
import com.infobasic.ciavarella.nexttechacademy.spring_jwt.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthDataLoadRunner implements CommandLineRunner {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    AuthRoleRepository authRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if roles already exist
        if (authRoleRepository.count() == 0) {
            Role r_admin = new Role();
            r_admin.setName(ERole.ROLE_ADMIN);
            authRoleRepository.save(r_admin);

            Role r_student = new Role();
            r_student.setName(ERole.ROLE_STUDENT);
            authRoleRepository.save(r_student);

            Role r_teacher = new Role();
            r_teacher.setName(ERole.ROLE_TEACHER);
            authRoleRepository.save(r_teacher);

            // Create users with roles
            AuthUser u_teacher = new AuthUser("teacherEmail@gmail.com", encoder.encode("mygoodpassword"), r_teacher);
            authUserRepository.save(u_teacher);

            AuthUser u_admin = new AuthUser("adminEmail@gmail.com", encoder.encode("mygoodpassword"), r_admin);
            authUserRepository.save(u_admin);

            AuthUser u_student = new AuthUser("studentEmail@gmail.com", encoder.encode("mygoodpassword"), r_student);
            authUserRepository.save(u_student);
        }
    }
}
