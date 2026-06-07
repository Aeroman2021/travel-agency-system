package com.myproject.model.view;

import com.myproject.model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vw_flights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightView {

    @Id
    private Long id;

    private String airlineName;

    private String flightNumber;

    private String originAirportDisplay;

    private String destAirportDisplay;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private BigDecimal price;

    private int availableSeats;

    private String currencyCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FlightStatus status;



}
