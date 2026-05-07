package com.example.pcpracticalab.Controller;
import com.example.pcpracticalab.DTOs.LoginRequestDTO;
import com.example.pcpracticalab.DTOs.LoginResponseDTO;
import com.example.pcpracticalab.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/login")
public class AuthController {
    private final AuthService authService;
    @PostMapping
    public ResponseEntity<LoginResponseDTO> createAuth(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.createAuth(request));
    }
}
