package com.myproject.application.impl;

import com.myproject.application.PassengerFacade;
import com.myproject.model.dto.PassengerResponseDto;
import com.myproject.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerFacadeImpl implements PassengerFacade {

    private final PassengerRepository passengerRepository;

    @Override
    public List<PassengerResponseDto> getPassengerByBookId(Long bookId) {
        return passengerRepository.getPassengersByBookingId(bookId);
    }

    @Override
    public void deletePassengerByBookingId(Long bookingId) {
        passengerRepository.deletePassengerByBookingId(bookingId);
    }

    @Override
    public void cancelPassengerByBookingId(Long bookingId) {

    }

}
