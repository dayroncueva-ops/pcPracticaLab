package com.example.pcpracticalab.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="vuelos")
@Getter
@Setter
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlineName;

    @Column(unique = true)
    private String flightNumber;

    private Date estDepartureTime;
    private Date estArrivalTime;

    private Integer availableSeats;
}