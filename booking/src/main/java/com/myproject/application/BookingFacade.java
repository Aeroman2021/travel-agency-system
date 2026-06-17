package com.myproject.application;

import com.myproject.model.dto.response.BookingResponseDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface BookingFacade {
    BigDecimal getTotalPriceById(Long bookingId);
    void failBooking(Long bookingId);
    void cancelBooking(Long bookingId);
    Optional<BookingResponseDto> getById(Long bookingId);
}
