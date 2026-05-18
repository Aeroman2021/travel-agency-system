package com.myproject.model.dto.response;

import com.myproject.model.enums.FlightStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record FlightResponseDto(

        Long id,

        String flightNumber,

        Long airLine,

        Long originAirport,

        Long destinationAirport,

        LocalDateTime departureTime,

        LocalDateTime arrivalTime,

        BigDecimal price,

        String currencyCode,

        int availableSeats,

        FlightStatus status
) {
}
