package com.myproject.application;

import java.math.BigDecimal;

public interface BookingFacade {
    BigDecimal getTotalPriceById(Long bookingId);
    void failBooking(Long bookingId);
    void cancelBooking(Long bookingId);
}
