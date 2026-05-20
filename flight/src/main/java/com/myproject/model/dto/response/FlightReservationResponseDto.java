package com.myproject.model.dto.response;

import com.myproject.model.enums.FlightReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class FlightReservationResponseDto {

    private Long id;

    private Long bookingId;

    private Long flightId;

    private Long airlineId;

    private String flightNumber;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private BigDecimal price;

    private FlightReservationStatus flightReservationStatus;

    private String pnr;
}
