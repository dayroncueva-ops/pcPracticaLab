package com.example.pcpracticalab.Controller;

import com.example.pcpracticalab.DTOs.UserRequestDTO;
import com.example.pcpracticalab.DTOs.UserResponseDTO;
import com.example.pcpracticalab.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(dto));
    }
}
