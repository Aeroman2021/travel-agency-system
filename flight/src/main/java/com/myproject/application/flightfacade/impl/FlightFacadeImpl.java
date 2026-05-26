package com.myproject.application.flightfacade.impl;

import com.myproject.application.flightfacade.FlightFacade;
import com.myproject.exception.InvalidInputSeatNumber;
import com.myproject.exception.NoAvailableSeatsException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.entity.Flight;
import com.myproject.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FlightFacadeImpl implements FlightFacade {

    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public void releaseSeats(Long flightId, int seatCount) {
        var flight = getById(flightId);
        var updatedSeat = getById(flightId).getAvailableSeats() + seatCount;
        flight.setAvailableSeats(updatedSeat);
    }

    public Flight getById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Flight", flightId)));
    }

    @Override
    public BigDecimal calculateTotalPrice(Long flightId, int passengerCount) {
        validatePassengersCount(passengerCount);
        hasAvailableSeats(flightId,passengerCount);
        return getFlightOrThrow(flightId).getPrice().multiply(BigDecimal.valueOf(passengerCount));
    }

    @Override
    public void hasAvailableSeats(Long flightId, int passengerCount) {

        validatePassengersCount(passengerCount);

        if(getFlightOrThrow(flightId).getAvailableSeats() < passengerCount)
            throw new NoAvailableSeatsException("Not enough seat available");
    }

    private static void validatePassengersCount(int passengerCount) {
        if (passengerCount <= 0)
            throw new InvalidInputSeatNumber("Passenger count must be a positive Number.");
    }

    private @NonNull Flight getFlightOrThrow(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Flight", flightId)));
    }

}
