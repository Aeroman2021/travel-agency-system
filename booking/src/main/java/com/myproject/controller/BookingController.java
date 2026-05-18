package com.myproject.controller;

import com.myproject.model.dto.request.BookingRequestDto;
import com.myproject.model.dto.response.BookingResponseDto;
import com.myproject.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> save(@RequestBody BookingRequestDto dto) {
        var savedBooking = bookingService.save(dto);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

}
