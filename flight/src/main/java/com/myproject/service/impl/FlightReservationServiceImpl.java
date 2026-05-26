package com.myproject.service.impl;

import com.myproject.model.dto.response.FlightReservationResponseDto;
import com.myproject.model.entity.FlightReservation;
import com.myproject.model.enums.FlightReservationStatus;
import com.myproject.event.BookingInitiatedEvent;
import com.myproject.event.FlightReservedEvent;
import com.myproject.model.maper.FlightReservationMapper;
import com.myproject.repository.FlightReservationRepository;
import com.myproject.service.FlightReservationService;
import com.myproject.service.FlightService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightReservationServiceImpl implements FlightReservationService {

    private final FlightReservationRepository flightReservationRepository;
    private final FlightService flightservice;
    private final FlightReservationMapper flightReservationMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public FlightReservationResponseDto reserveFlight(BookingInitiatedEvent event) {
        flightservice.reservedSeats(event.flightId(), event.passengerCount());
        var flightReservation = flightReservationCreator(event);
        var savedFlightReservation = flightReservationRepository.save(flightReservation);

        eventPublisher.publishEvent(new FlightReservedEvent(
                savedFlightReservation.getFlightId(),
                event.bookingId(),
                savedFlightReservation.getPrice(),
                event.passengerCount()));
        return flightReservationMapper.toDto(savedFlightReservation);
    }

    private @NonNull FlightReservation flightReservationCreator(BookingInitiatedEvent event) {
        var flight = flightservice.getById(event.flightId());
        var flightReservation = new FlightReservation();
        flightReservation.setBookingId(event.bookingId());
        flightReservation.setFlightId(event.flightId());
        flightReservation.setFlightNumber(flight.flightNumber());
        flightReservation.setAirlineId(flight.airLine());
        flightReservation.setDepartureTime(flight.departureTime());
        flightReservation.setArrivalTime(flight.arrivalTime());
        flightReservation.setStatus(FlightReservationStatus.RESERVED);
        flightReservation.setPrice(totalPriceCalculator(event.passengerCount(), flight.price()));
        flightReservation.setPnr(createPnr());

        return flightReservation;
    }

    private String createPnr() {
        return UUID.randomUUID().toString().replace("-","").substring(0, 8).toUpperCase();
    }

    private BigDecimal totalPriceCalculator(int passengerCount, BigDecimal flightPrice) {
        return flightPrice.multiply(BigDecimal.valueOf(passengerCount));
    }
}
