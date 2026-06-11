package com.myproject.service.impl;

import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.response.FlightResponseDto;
import com.myproject.model.maper.FlightMapper;
import com.myproject.repository.FlightRepository;
import com.myproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

}
