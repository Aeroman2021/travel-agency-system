package com.myproject.application.impl;

import com.myproject.application.BookingFacade;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.response.BookingResponseDto;
import com.myproject.model.entity.Booking;
import com.myproject.model.enums.BookingStatus;
import com.myproject.model.mapper.BookingMapper;
import com.myproject.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BigDecimal getTotalPriceById(Long bookingId) {
        var booking = getBookingById(bookingId);
        return booking.getTotalPrice();
    }


    @Override
    @Transactional
    public void failBooking(Long bookingId) {
        var booking = getBookingById( bookingId);
        booking.setBookingStatus(BookingStatus.FAILED);
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        var booking = getBookingById( bookingId);
        booking.setBookingStatus(BookingStatus.CANCELLED);
    }


    private @NonNull Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Booking", bookingId)));
    }

    @Override
    public Optional<BookingResponseDto> getById(Long bookingId) {
        var booking =  bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Booking", bookingId)));
        return Optional.ofNullable(bookingMapper.toDto(booking));
    }
}
