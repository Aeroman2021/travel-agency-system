package com.myproject.service.impl;

import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.response.FlightResvPasgsResponseDto;
import com.myproject.model.entity.FlightReservationPassenger;
import com.myproject.event.TicketSuccessfullyIssuedEvent;
import com.myproject.model.maper.FlightReservationPassengerMapper;
import com.myproject.repository.FlightReservationRepository;
import com.myproject.repository.FlightResvPsgrsRepository;
import com.myproject.service.FlightResvPsgrsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightResvPagsServiceImpl implements FlightResvPsgrsService {

    private final FlightResvPsgrsRepository flightResvPsgrsRepository;
    private final FlightReservationRepository flightReservationRepository;
    private final FlightReservationPassengerMapper flightReservationPassengerMapper;


    @Override
    public List<FlightResvPasgsResponseDto> save(TicketSuccessfullyIssuedEvent event) {

        var flightReservationId = getFlightReservationId(event);

        return event.flightReservationDtoList().stream()
                .map(dto -> {
                    var flightReservationPassenger = new FlightReservationPassenger();
                    flightReservationPassenger.setPassengerId(dto.getPassengerId());
                    flightReservationPassenger.setTicketNumber(dto.getTicketNumber());
                    flightReservationPassenger.setFlightReservationId(flightReservationId);
                    flightReservationPassenger.setSeatNumber(generateSeatNumber());
                    return flightResvPsgrsRepository.save(flightReservationPassenger);
                })
                .map(flightReservationPassengerMapper::toDto)
                .toList();
    }

    private Long getFlightReservationId(TicketSuccessfullyIssuedEvent event) {
        var bookingId = event.flightReservationDtoList().getFirst().getBookingId();
         var flightReservation =   flightReservationRepository.findByBookingId(bookingId)
                 .orElseThrow(() -> new ResourceNotFoundException("%s with bookingId %d not found"
                .formatted("Flight Reservation", bookingId)));
         return flightReservation.getId();
    }

    private String generateSeatNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
    }
}
