package com.example.pcpracticalab.Service;

import com.example.pcpracticalab.DTOs.VueloRequestDTO;
import com.example.pcpracticalab.DTOs.VueloResponseDTO;
import com.example.pcpracticalab.Entity.Vuelo;
import com.example.pcpracticalab.Repository.VueloRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class VueloService {
    public final VueloRepository vueloRepository;

    public VueloResponseDTO createVuelo(VueloRequestDTO dto) {

        if (vueloRepository.existsByFlightNumber(dto.getFlightNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight number already exists.");
        }

        if (dto.getAvailableSeats() == null || dto.getAvailableSeats() <= 0) {
            throw new RuntimeException("N° Asientos disponibles mayor que 0");
        }

        if (dto.getEstDepartureTime().after(dto.getEstArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departure must be before arrival");
        }

        Vuelo vuelo = new Vuelo();
        vuelo.setAirlineName(dto.getAirlineName());
        vuelo.setFlightNumber(dto.getFlightNumber());
        vuelo.setEstDepartureTime(dto.getEstDepartureTime());
        vuelo.setEstArrivalTime(dto.getEstArrivalTime());
        vuelo.setAvailableSeats(dto.getAvailableSeats());

        Vuelo saved = vueloRepository.save(vuelo);

        VueloResponseDTO res = new VueloResponseDTO();
        res.setId(saved.getId());
        res.setAirlineName(saved.getAirlineName());
        res.setFlightNumber(saved.getFlightNumber());
        res.setEstDepartureTime(saved.getEstDepartureTime());
        res.setEstArrivalTime(saved.getEstArrivalTime());
        res.setAvailableSeats(saved.getAvailableSeats());

        return res;
    }
    // searching flights
    public List<VueloResponseDTO> searchFlights (
        String flightNumer, String airlineName, Date startDate, Date endDate
    ) {
        List<Vuelo> vuelos;
        if (flightNumer != null) {
            vuelos = vueloRepository.findByFlightNumberContaining(flightNumer);
        }
        else if (airlineName!=null) {
            vuelos = vueloRepository.findByAirlineNameContaining(airlineName);
        } else if (startDate!=null && endDate!=null) {
            vuelos = vueloRepository.findByEstDepartureTimeBetween(startDate, endDate);
        } else {
            vuelos = vueloRepository.findAll();
        }
        return vuelos.stream().map(v->{
            VueloResponseDTO dto = new VueloResponseDTO();

            dto.setAirlineName(v.getAirlineName());
            dto.setFlightNumber(v.getFlightNumber());
            dto.setEstArrivalTime(v.getEstArrivalTime());
            dto.setEstDepartureTime(v.getEstDepartureTime());
            dto.setAvailableSeats(v.getAvailableSeats());
            return dto;
        }).toList();
    }
}
