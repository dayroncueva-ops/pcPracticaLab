package com.example.pcpracticalab.DTOs;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VueloRequestDTO {

    @NotBlank
    private String airlineName;

    @Pattern(regexp = "^[A-Z]{2,3}[0-9]{3}$")
    @NotBlank
    private String flightNumber;

    @NotNull
    private Date estDepartureTime;

    @NotNull
    private Date estArrivalTime;

    @NotNull
    @Min(1)
    private Integer availableSeats;
}