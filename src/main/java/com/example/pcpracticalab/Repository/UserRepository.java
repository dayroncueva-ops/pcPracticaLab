package com.example.pcpracticalab.Repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.example.pcpracticalab.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
