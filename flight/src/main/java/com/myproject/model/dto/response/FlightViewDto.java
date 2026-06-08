package com.myproject.model.dto.response;

import com.myproject.model.enums.CabinClass;
import com.myproject.model.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class FlightViewDto{
    private Long id;

    private String airlineName;

    private String flightNumber;

    private String originAirportDisplay;

    private String destAirportDisplay;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private BigDecimal startingPrice;

    private String currencyCode;

    private Long totalAvailableSeats;

    private List<CabinClass> availableCabins;

    private FlightStatus status;
}

