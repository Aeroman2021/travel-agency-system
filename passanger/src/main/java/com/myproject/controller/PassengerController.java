package com.myproject.controller;


import com.myproject.model.dto.PassengerRequestDto;
import com.myproject.model.dto.PassengerResponseDto;
import com.myproject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passegers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;
    @PostMapping("/{booking-id}")
    public ResponseEntity<PassengerResponseDto> save(
            @PathVariable("booking-id") Long bookingId,
            @RequestBody PassengerRequestDto requestDto){
        requestDto.setBookingId(bookingId);
        var flight = passengerService.save(requestDto);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);

    }
}
