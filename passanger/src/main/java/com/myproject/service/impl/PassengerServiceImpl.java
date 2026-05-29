package com.myproject.service.impl;

import com.myproject.event.PassengerRegisteredEvent;
import com.myproject.event.progressevents.SagaPassengerRegisteredEvent;
import com.myproject.event.compensationevents.CancelPassengerEvent;
import com.myproject.model.dto.InputPassengers;
import com.myproject.model.dto.response.PassengerResponseDto;
import com.myproject.model.enums.PassengerStatus;
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

        eventPublisher.publishEvent(
                new PassengerRegisteredEvent(inputPassengers.getBookingId()));

        eventPublisher.publishEvent(
                new SagaPassengerRegisteredEvent(inputPassengers.getBookingId()));

        return passengerResponseDtos;
    }

    @Override
    public void cancelPassenger(CancelPassengerEvent event) {
        passengerRepository.findPassengersByBookingId(event.bookingId())
                .forEach(e -> e.setPassengerStatus(PassengerStatus.CANCELLED));
    }

    @Override
    public List<PassengerResponseDto> getPassengersByBookingId(Long bookingId) {
        return passengerRepository.getPassengersByBookingId(bookingId)
                .stream()
                .map(passengerMapper::toDto)
                .toList();
    }

}
