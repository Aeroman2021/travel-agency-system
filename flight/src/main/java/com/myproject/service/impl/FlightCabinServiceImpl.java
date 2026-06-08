package com.myproject.service.impl;

import com.myproject.exception.NoAvailableSeatsException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.exception.SeatAlreadyReservedException;
import com.myproject.model.dto.response.FlightCabinResponse;
import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;
import com.myproject.model.maper.FlightCabinMapper;
import com.myproject.repository.FlightCabinRepository;
import com.myproject.service.FlightCabinService;
import com.myproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FlightCabinServiceImpl implements FlightCabinService {

    private final FlightService flightService;
    private final FlightCabinRepository flightCabinRepository;
    private final FlightCabinMapper flightCabinMapper;

    @Override
    public FlightCabinResponse getById(Long id) {
        FlightCabin flightCabin = flightCabinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Flight cabin", id)));
        return flightCabinMapper.toDto(flightCabin);
    }

    @Override
    public FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass) {
        return flightCabinRepository.findByFlightIdAndCabinClass(flightId, cabinClass);
    }

    @Override
    public void reservedSeats(Long flightId, int passengerCount, CabinClass cabinClass) {
        try {
            var flight = flightService.getById(flightId);
            var flightCabin = findByFlightIdAndCabinClass(flightId, cabinClass);
            if (flightCabin.getAvailableSeats() < passengerCount)
                throw new NoAvailableSeatsException("There is not enough seat available");
            flightCabin.setAvailableSeats(flightCabin.getAvailableSeats() - passengerCount);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new SeatAlreadyReservedException("This seat has bean already reserved.");
        }
    }

    @Override
    public BigDecimal calculateTotalPrice(Long flightId, CabinClass cabinClass, int passengerCount) {
        var flightCabin = findByFlightIdAndCabinClass(flightId, cabinClass);
        return flightCabin.getPrice().multiply(new BigDecimal(passengerCount));
    }
}
