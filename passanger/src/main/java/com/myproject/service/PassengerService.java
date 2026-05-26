package com.myproject.service;

import com.myproject.model.dto.InputPassengers;
import com.myproject.model.dto.response.PassengerResponseDto;

import java.util.List;

public interface PassengerService {
    List<PassengerResponseDto> save(InputPassengers inputPassengers);
    void cancelPassengerByBookingId(Long bookingId);
    List<PassengerResponseDto> getPassengersByBookingId(Long bookingId);

}
