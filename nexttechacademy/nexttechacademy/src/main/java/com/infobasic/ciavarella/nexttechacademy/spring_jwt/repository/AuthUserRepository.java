package com.infobasic.ciavarella.nexttechacademy.spring_jwt.repository;

import com.infobasic.ciavarella.nexttechacademy.spring_jwt.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
  
  Optional<AuthUser> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
