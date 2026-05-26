package com.myproject.application;

import com.myproject.model.dto.PassengerResponseDto;

import java.util.List;

public interface PassengerFacade {
    List<PassengerResponseDto> getPassengerByBookId(Long bookId);
    void deletePassengerByBookingId(Long bookingId);
}
