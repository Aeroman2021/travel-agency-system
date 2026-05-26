package com.myproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flight_reservation_passengers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightReservationPassenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flightReservationId;

    private Long passengerId;

    private String seatNumber;

    private String ticketNumber;
}
