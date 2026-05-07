package com.example.pcpracticalab.Service;

import com.example.pcpracticalab.DTOs.UserRequestDTO;
import com.example.pcpracticalab.DTOs.UserResponseDTO;
import com.example.pcpracticalab.Entity.User;
import com.example.pcpracticalab.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(dto.getEmail()); // 🔥 CLAVE: email = username
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saved = userRepository.save(user);
        userRepository.flush();

        return new UserResponseDTO(saved.getId());
    }
}