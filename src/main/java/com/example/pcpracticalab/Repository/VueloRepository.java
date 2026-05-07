package com.example.pcpracticalab.Repository;

import com.example.pcpracticalab.DTOs.VueloRequestDTO;
import com.example.pcpracticalab.DTOs.VueloResponseDTO;
import com.example.pcpracticalab.Entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo,Long> {
    boolean existsByFlightNumber(String flightNumber);
    List<Vuelo> findByFlightNumberContaining(String flightNumber);
    List<Vuelo> findByAirlineNameContaining(String airlineName);
    List<Vuelo> findByEstDepartureTimeBetween(Date startDate, Date endDate);
}
