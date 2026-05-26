package com.myproject.service;

import com.myproject.model.dto.InputPassengers;
import com.myproject.model.dto.PassengerResponseDto;

import java.util.List;

public interface PassengerService {
    List<PassengerResponseDto> save(InputPassengers inputPassengers);
    void deletePassengerByBookingId(Long bookingId);
}
