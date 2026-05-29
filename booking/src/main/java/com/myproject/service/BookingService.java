package com.myproject.service;

import com.myproject.event.compensationevents.CancelBookingEvent;
import com.myproject.model.dto.request.BookingRequestDto;
import com.myproject.model.dto.response.BookingResponseDto;

public interface BookingService {
    BookingResponseDto save(BookingRequestDto requestDto);
    void cancelBookingEvent(CancelBookingEvent event);
}
