package com.myproject.model.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlightResvPasgsResponseDto {

     Long id;

     Long flightReservationId;

     Long passengerId;

     String seatNumber;

     String ticketNumber;
}
