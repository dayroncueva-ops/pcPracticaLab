package com.example.pcpracticalab.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VueloResponseDTO {
    private Long id;
    private String airlineName;
    private String flightNumber;
    private Date estDepartureTime;
    private Date estArrivalTime;
    private Integer availableSeats;
}