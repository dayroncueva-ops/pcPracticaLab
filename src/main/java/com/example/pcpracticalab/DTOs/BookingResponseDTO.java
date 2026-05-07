package com.example.pcpracticalab.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BookingResponseDTO {
    private Long id;
    private Long flightId;
    private String userEmail;
    private Date bookingDate;

}
