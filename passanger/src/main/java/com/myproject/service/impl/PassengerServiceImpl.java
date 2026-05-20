package com.myproject.service.impl;

import com.myproject.model.dto.PassengerRequestDto;
import com.myproject.model.dto.PassengerResponseDto;
import com.myproject.model.mapper.PassengerMapper;
import com.myproject.repository.PassengerRepository;
import com.myproject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Override
    public PassengerResponseDto save(PassengerRequestDto requestDto) {
        var passenger  = passengerMapper.toEntity(requestDto);
        var savedPassenger = passengerRepository.save(passenger);
        return passengerMapper.toDto(savedPassenger);
    }
}
