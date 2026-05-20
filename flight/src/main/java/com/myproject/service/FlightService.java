package com.myproject.service;

import com.myproject.model.dto.response.FlightResponseDto;
import org.springframework.data.domain.Page;


public interface FlightService {
    Page<FlightResponseDto> getAll(String pageNumber,String pageSize);
    FlightResponseDto getById(Long flightId);
    void reservedSeats(Long flightId, int passengerCount);


}
