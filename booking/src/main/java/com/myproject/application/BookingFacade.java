package com.myproject.application;

import java.math.BigDecimal;

public interface BookingFacade {
    int getPassengersCountById(Long bookingId);
    BigDecimal getTotalPriceById(Long bookingId);
}
