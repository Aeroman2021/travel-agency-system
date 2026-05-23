package com.myproject.application.impl;

import com.myproject.application.PassengerFacade;
import com.myproject.model.dto.PassengerResponseDto;
import com.myproject.model.mapper.PassengerMapper;
import com.myproject.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerFacadeImpl implements PassengerFacade {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Override
    public List<PassengerResponseDto> getPassengerByBookId(Long bookId) {
        return passengerRepository.getPassengersByBookingId(bookId).stream()
                .map(passengerMapper::toDto)
                .toList();
    }
}
