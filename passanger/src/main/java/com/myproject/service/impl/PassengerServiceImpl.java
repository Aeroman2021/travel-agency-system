package com.myproject.service.impl;

import com.myproject.event.PassengerRegisteredEvent;
import com.myproject.model.dto.InputPassengers;
import com.myproject.model.dto.PassengerResponseDto;
import com.myproject.model.mapper.PassengerMapper;
import com.myproject.repository.PassengerRepository;
import com.myproject.service.PassengerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public List<PassengerResponseDto> save(InputPassengers inputPassengers) {

        List<PassengerResponseDto> passengerResponseDtos =
                inputPassengers.getPassengerRequestDtoList()
                .stream()
                .map(dto -> {
                    var passenger = passengerMapper.toEntity(dto);
                    passenger.setBookingId(inputPassengers.getBookingId());
                    return passengerRepository.save(passenger);
                })
                .map(passengerMapper::toDto)
                .toList();

        System.out.println("PUBLISHING EVENT");

        eventPublisher.publishEvent(
                new PassengerRegisteredEvent(inputPassengers.getBookingId()));

        return passengerResponseDtos;
    }
}
