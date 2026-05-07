package com.example.pcpracticalab.Controller;

import com.example.pcpracticalab.Repository.BookingRepository;
import com.example.pcpracticalab.Repository.UserRepository;
import com.example.pcpracticalab.Repository.VueloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CleanUpController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final VueloRepository vueloRepository;

    @DeleteMapping("/cleanup")
    public ResponseEntity<String> cleanup() {

        bookingRepository.deleteAll();
        vueloRepository.deleteAll();
        userRepository.deleteAll();

        return ResponseEntity.ok("Database cleaned");
    }
}
