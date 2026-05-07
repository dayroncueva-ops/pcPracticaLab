package com.example.pcpracticalab.Service;
import com.example.pcpracticalab.DTOs.LoginRequestDTO;
import com.example.pcpracticalab.DTOs.LoginResponseDTO;
import com.example.pcpracticalab.Entity.User;
import com.example.pcpracticalab.Repository.UserRepository;
import com.example.pcpracticalab.Exception.AuthException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public LoginResponseDTO createAuth(LoginRequestDTO request) {

        if (request.getEmail() == null || request.getPassword() == null) {
            throw new AuthException("Missing credentials");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found"));

        if (user.getPassword() == null ||
                !user.getPassword().equals(request.getPassword())) {
            throw new AuthException("Incorrect password");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(jwtService.generateToken(user));

        return response;
    }
}
