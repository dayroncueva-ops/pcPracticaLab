package com.example.pcpracticalab.Service;

import com.example.pcpracticalab.DTOs.BookingRequestDTO;
import com.example.pcpracticalab.DTOs.BookingResponseDTO;
import com.example.pcpracticalab.Entity.Booking;
import com.example.pcpracticalab.Entity.User;
import com.example.pcpracticalab.Entity.Vuelo;
import com.example.pcpracticalab.Repository.BookingRepository;
import com.example.pcpracticalab.Repository.UserRepository;
import com.example.pcpracticalab.Repository.VueloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final VueloRepository vueloRepository;
    public BookingResponseDTO reservaVuelo (BookingRequestDTO requestDTO, String userEmail){
        // 1 verificar si estas autenticado
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()->new RuntimeException("User not found"));
        // 2 verificar si hay vuelo
        Vuelo vuelo = vueloRepository.findById(requestDTO.getFlightId())
                .orElseThrow(()->new RuntimeException("Flight not found"));
        // 3 sin asientos
        if (vuelo.getAvailableSeats()<=0) {
            throw new RuntimeException("No available seats");
        }
        if(vuelo.getEstDepartureTime().before(new Date())){
            throw new RuntimeException("Flight already departed");
        }

        // 4 crear booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setVuelo(vuelo);
        booking.setBookingDate(new Date());
        // 5 reducir asientos
        vuelo.setAvailableSeats(vuelo.getAvailableSeats()-1);
        vueloRepository.save(vuelo);

        // 6 response
        BookingResponseDTO bookResponseDTO = new BookingResponseDTO();
        Booking saved = bookingRepository.save(booking);

        try {
            String fileName = "flight_booking_email_" + saved.getId() + ".txt";

            FileWriter writer = new FileWriter(fileName);

            writer.write("Flight Booking Confirmation\n");
            writer.write("----------------------------\n");

            writer.write("Passenger: "
                    + user.getFirstName()
                    + " "
                    + user.getLastName()
                    + "\n");

            writer.write("Email: " + user.getEmail() + "\n");

            writer.write("Flight Number: "
                    + vuelo.getFlightNumber()
                    + "\n");

            writer.write("Departure Time: "
                    + vuelo.getEstDepartureTime().toInstant()
                    + "\n");

            writer.write("Arrival Time: "
                    + vuelo.getEstArrivalTime().toInstant()
                    + "\n");

            writer.write("Booking Date: "
                    + saved.getBookingDate().toInstant()
                    + "\n");

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Error creating confirmation file");
        }

        bookResponseDTO.setId(saved.getId());
        bookResponseDTO.setFlightId(vuelo.getId());
        bookResponseDTO.setBookingDate(saved.getBookingDate());
        bookResponseDTO.setUserEmail(user.getEmail());
        return bookResponseDTO;
    }
}
