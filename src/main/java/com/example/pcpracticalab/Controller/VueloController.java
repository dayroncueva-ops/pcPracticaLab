package com.example.pcpracticalab.Controller;

import com.example.pcpracticalab.DTOs.VueloRequestDTO;
import com.example.pcpracticalab.DTOs.VueloResponseDTO;
import com.example.pcpracticalab.Entity.Vuelo;
import com.example.pcpracticalab.Service.VueloService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
@Getter
@Setter
public class VueloController {
    public final VueloService vueloService;
    @PostMapping("/create")
    public ResponseEntity<VueloResponseDTO> createVuelo(
            @Valid @RequestBody VueloRequestDTO dto) {

        VueloResponseDTO response = vueloService.createVuelo(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/search")
    public ResponseEntity<List<VueloResponseDTO>> searchFlights(
            @RequestParam(required = false) String flightNumber,
            @RequestParam(required = false) String airlineName,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {

        return ResponseEntity.ok(
                vueloService.searchFlights(
                        flightNumber,
                        airlineName,
                        startDate,
                        endDate
                )
        );
    }
}
