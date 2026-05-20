package com.myproject.model.entity;

import com.myproject.model.enums.FlightReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight_reservations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private Long flightId;

    private Long airlineId;

    private String flightNumber;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private BigDecimal price;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private FlightReservationStatus status;

    private String pnr;

}
