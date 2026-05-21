package com.myproject.application.impl;

import com.myproject.application.BookingFacade;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    private final BookingRepository bookingRepository;

    @Override
    public int getPassengersCountById(Long bookingId) {
        var booking =  bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Booking", bookingId)));
        return booking.getNumberOfPassengers();
    }

    @Override
    public BigDecimal getTotalPriceById(Long bookingId) {
        var booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Booking", bookingId)));
        return booking.getTotalPrice();
    }
}
