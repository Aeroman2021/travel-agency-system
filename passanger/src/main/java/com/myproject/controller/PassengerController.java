package com.myproject.controller;


import com.myproject.model.dto.InputPassengers;
import com.myproject.model.dto.request.PassengerRequestDto;
import com.myproject.model.dto.response.PassengerResponseDto;
import com.myproject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passegers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @PostMapping("booking/{booking-id}/passengers")
    public ResponseEntity<List<PassengerResponseDto>> save(
            @PathVariable("booking-id") Long bookingId,
            @RequestBody List<PassengerRequestDto> requestDtoList) {
        var savedPassengers = passengerService.save(new InputPassengers(requestDtoList, bookingId));
        return new ResponseEntity<>(savedPassengers, HttpStatus.CREATED);
    }
}
