package com.myproject.application;

import com.myproject.exception.InvalidInputSeatNumber;
import com.myproject.exception.NoAvailableSeatsException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.entity.Flight;
import com.myproject.repository.FlightRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FlightFacadeImpl implements FlightFacade {

    private final FlightRepository flightRepository;

    @Override
    public FlightPricingDto getFlightPrice(Long flightId) {
        var flight = getFlightOrThrow(flightId);
        return new FlightPricingDto(flightId,flight.getPrice());
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
