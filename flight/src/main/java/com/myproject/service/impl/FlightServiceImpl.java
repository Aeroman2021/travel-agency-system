package com.myproject.service.impl;

import com.myproject.exception.NoAvailableSeatsException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.exception.SeatAlreadyReservedException;
import com.myproject.model.dto.response.FlightResponseDto;
import com.myproject.model.maper.FlightMapper;
import com.myproject.repository.FlightRepository;
import com.myproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public Page<FlightResponseDto> getAll(String pageNumber, String pageSize) {
        var pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        var flights = flightRepository.findAll(pageable)
                .stream()
                .map(FlightMapper::toDto)
                .toList();
        return new PageImpl<>(flights, pageable, flights.size());
    }

    @Override
    public FlightResponseDto getById(Long flightId) {
        var flight =  flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Flight", flightId)));
        return FlightMapper.toDto(flight);
    }

    @Override
    public void reservedSeats(Long flightId, int passengerCount) {
        try{
            var flight =  flightRepository.findById(flightId)
                    .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                            .formatted("Flight", flightId)));
            if(flight.getAvailableSeats() < passengerCount)
                throw new NoAvailableSeatsException("There is not enough seat available");

            flight.setAvailableSeats(flight.getAvailableSeats() - passengerCount);
        }catch (ObjectOptimisticLockingFailureException e){
            throw new SeatAlreadyReservedException("This seat has bean already reserved.");
        }
    }

}
