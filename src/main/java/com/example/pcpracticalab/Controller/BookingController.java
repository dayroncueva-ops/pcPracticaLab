package com.example.pcpracticalab.Controller;

import com.example.pcpracticalab.DTOs.BookingRequestDTO;
import com.example.pcpracticalab.DTOs.BookingResponseDTO;
import com.example.pcpracticalab.Entity.Booking;
import com.example.pcpracticalab.Repository.BookingRepository;
import com.example.pcpracticalab.Service.BookingService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flights/book")
public class BookingController {
    public final BookingService bookingService;
    public final BookingRepository bookingRepository;
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBooking(@PathVariable Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Booking not found"));
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setUserEmail(booking.getUser().getEmail());
        response.setFlightId(booking.getVuelo().getId());
        response.setBookingDate(booking.getBookingDate());
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<BookingResponseDTO> reservaVuelo (@RequestBody BookingRequestDTO requestDTO, Authentication authentication) {
        String userEmail = authentication.getName();
        BookingResponseDTO book = bookingService.reservaVuelo(requestDTO,userEmail);
        return ResponseEntity.ok(book);
    }
}
