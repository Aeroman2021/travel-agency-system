package com.myproject.service.impl;

import com.myproject.model.dto.response.FlightResvPasgsResponseDto;
import com.myproject.model.entity.FlightReservationPassenger;
import com.myproject.model.event.TicketSuccessfullyIssuedEvent;
import com.myproject.repository.FlightReservationRepository;
import com.myproject.repository.FlightResvPsgrsRepository;
import com.myproject.service.FlightResvPsgrsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightResvPagsServiceImpl implements FlightResvPsgrsService {

    private final FlightResvPsgrsRepository flightResvPsgrsRepository;
    private final FlightReservationRepository flightReservationRepository;


    @Override
    public FlightResvPasgsResponseDto save(TicketSuccessfullyIssuedEvent event) {

        var reservationId = flightReservationRepository.
                findByBookingId(event.flightReservationDto().
                        getBookingId())
                .getId();

        var flightReservationPassenger = new FlightReservationPassenger();
        flightReservationPassenger.setFlightReservationId(reservationId);

        for (String s : event.flightReservationDto().getTicketNumebrList()) {

        }

        return null;


    }
}
