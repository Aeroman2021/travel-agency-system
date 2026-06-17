package com.myproject.service;

import com.myproject.event.compensationevents.CancelPassengerEvent;
import com.myproject.model.dto.InputPassengers;
import com.myproject.model.dto.response.PassengerResponseDto;

import java.util.List;

public interface PassengerService {
    List<PassengerResponseDto> save(InputPassengers inputPassengers,Long bookingId);
    List<PassengerResponseDto> getPassengersByBookingId(Long bookingId);
    void cancelPassenger(CancelPassengerEvent  event);

}
