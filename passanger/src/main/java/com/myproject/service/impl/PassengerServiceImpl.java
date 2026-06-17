package com.myproject.service.impl;

import com.myproject.application.BookingFacade;
import com.myproject.event.PassengerRegisteredEvent;
import com.myproject.event.compensationevents.CancelPassengerEvent;
import com.myproject.event.progressevents.SagaPassengerRegisteredEvent;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.InputPassengers;
import com.myproject.model.dto.response.PassengerResponseDto;
import com.myproject.model.entity.Passenger;
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
    private final BookingFacade bookingFacade;

    @Override
    @Transactional
    public List<PassengerResponseDto> save(InputPassengers inputPassengers, Long bookingId) {

        bookingFacade.getById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Booking", bookingId)));

        List<Passenger> passengers = inputPassengers.getPassengerRequestDtoList()
                .stream()
                .map(dto -> {
                    var passenger = passengerMapper.toEntity(dto);
                    passenger.setBookingId(bookingId);
                    return passenger;
                })
                .toList();

        List<Passenger> savedPassengers = passengerRepository.saveAll(passengers);

        eventPublisher.publishEvent(new PassengerRegisteredEvent(bookingId));
        eventPublisher.publishEvent(new SagaPassengerRegisteredEvent(bookingId));

        return savedPassengers
                .stream()
                .map(passengerMapper::toDto)
                .toList();
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
