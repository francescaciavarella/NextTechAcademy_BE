package com.infobasic.ciavarella.nexttechacademy.spring_jwt.repository;

import com.infobasic.ciavarella.nexttechacademy.spring_jwt.model.ERole;
import com.infobasic.ciavarella.nexttechacademy.spring_jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AuthRoleRepository extends JpaRepository<Role, Long> {
  
  Optional<Role> findByName(ERole name);
}
