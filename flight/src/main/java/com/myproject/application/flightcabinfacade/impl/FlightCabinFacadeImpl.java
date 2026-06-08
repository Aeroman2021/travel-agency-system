package com.myproject.application.flightcabinfacade.impl;

import com.myproject.application.flightcabinfacade.FlightCabinFacade;
import com.myproject.exception.NoAvailableSeatsException;
import com.myproject.model.dto.response.FlightCabinResponse;
import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;
import com.myproject.service.FlightCabinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FlightCabinFacadeImpl implements FlightCabinFacade {
    private final FlightCabinService flightCabinService;

    @Override
    public FlightCabinResponse getById(Long id) {
        return flightCabinService.getById(id);
    }

    @Override
    public void releaseSeats(Long flightId, CabinClass cabinClass, int seatCount) {
        var flightCabin = flightCabinService.findByFlightIdAndCabinClass(flightId, cabinClass);
        var updatedSeats = flightCabin.getAvailableSeats() + seatCount;
        flightCabin.setAvailableSeats(updatedSeats);
    }

    @Override
    public BigDecimal calculateTotalPrice(Long flightId,CabinClass cabinClass, int passengerCount) {
        var flightCabin = flightCabinService.findByFlightIdAndCabinClass(flightId, cabinClass);
        return flightCabin.getPrice().multiply(new BigDecimal(passengerCount));
    }

    @Override
    public void hasAvailableSeats(Long flightId, CabinClass cabinClass, int passengerCount) {
            var flightCabin = flightCabinService.findByFlightIdAndCabinClass(flightId, cabinClass);
            if (flightCabin.getAvailableSeats() < passengerCount)
                throw new NoAvailableSeatsException("There is not enough seat available");
    }

    @Override
    public FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass) {
        return flightCabinService.findByFlightIdAndCabinClass(flightId, cabinClass);
    }

}
