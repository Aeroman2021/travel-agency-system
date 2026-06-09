package com.myproject.service.impl;

import com.myproject.exception.NoAvailableSeatsException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.exception.SeatAlreadyReservedException;
import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;
import com.myproject.repository.FlightCabinRepository;
import com.myproject.service.FlightCabinService;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FlightCabinServiceImpl implements FlightCabinService {

    private final FlightCabinRepository flightCabinRepository;

    @Override
    public FlightCabin getById(Long id) {
        return flightCabinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Flight cabin", id)));
    }

    @Override
    public FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass) {
        return flightCabinRepository.findByFlightIdAndCabinClass(flightId, cabinClass);
    }

    @Override
    public void reservedSeats(Long flightCabinId, int passengerCount) {
        try {

            var flightCabin = getById(flightCabinId);
            if (flightCabin.getAvailableSeats() < passengerCount)
                throw new NoAvailableSeatsException("There is not enough seat available");
            flightCabin.setAvailableSeats(flightCabin.getAvailableSeats() - passengerCount);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new SeatAlreadyReservedException("This seat has bean already reserved.");
        }
    }

    @Override
    public BigDecimal calculateTotalPrice(Long flightCabinId, int passengerCount) {
        var flightCabin = getById(flightCabinId);
        return flightCabin.getPrice().multiply(new BigDecimal(passengerCount));
    }

}
