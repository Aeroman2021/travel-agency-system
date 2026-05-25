package com.myproject.application.flightreservationfacade.impl;

import com.myproject.application.flightreservationfacade.FlightReservationFacade;
import com.myproject.exception.InvalidInputSeatNumber;
import com.myproject.exception.NoAvailableSeatsException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.entity.Flight;
import com.myproject.model.entity.FlightReservation;
import com.myproject.model.enums.FlightReservationStatus;
import com.myproject.repository.FlightRepository;
import com.myproject.repository.FlightReservationRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FlightReservationFacadeImpl implements FlightReservationFacade {
    private final FlightReservationRepository flightReservationRepository;

    @Override
    @Transactional
    public void cancelFlightReservation(Long flightReservationId) {
        getById(flightReservationId).setStatus(FlightReservationStatus.CANCELLED);
    }

    @Override
    @Transactional
    public void failFlightReservation(Long flightReservationId) {
        getById(flightReservationId).setStatus(FlightReservationStatus.CANCELLED);
    }

    public FlightReservation getById(Long flightReservationId) {
        return flightReservationRepository.findById(flightReservationId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("FlightReservation", flightReservationId)));
    }

}
