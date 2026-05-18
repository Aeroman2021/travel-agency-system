package com.myproject.application;

import com.myproject.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightFacadeImpl implements FlightFacade {
    private final FlightRepository flightRepository;


    @Override
    public FlightPricingDto getFlightPriceByFlightId(Long flightId) {
        var flight = flightRepository.findById(flightId).orElseThrow();
        return new FlightPricingDto(flightId,flight.getPrice());
    }
}
