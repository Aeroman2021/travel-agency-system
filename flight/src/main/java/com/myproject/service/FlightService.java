package com.myproject.service;

import com.myproject.model.dto.response.FlightResponseDto;
import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;


public interface FlightService {
    Page<FlightResponseDto> getAll(String pageNumber,String pageSize);
    FlightResponseDto getById(Long flightId);



}
