package com.myproject.application.impl;

import com.myproject.application.PassengerFacade;
import com.myproject.model.dto.response.PassengerResponseDto;
import com.myproject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerFacadeImpl implements PassengerFacade {

    private final PassengerService passengerService;

    @Override
    public List<PassengerResponseDto> getPassengerByBookId(Long bookId) {
        return passengerService.getPassengersByBookingId(bookId);
    }

    @Override
    public void cancelPassengerByBookingId(Long bookingId) {
        passengerService.cancelPassengerByBookingId(bookingId);
    }

}
