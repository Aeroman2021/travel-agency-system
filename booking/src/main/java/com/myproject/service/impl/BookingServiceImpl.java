package com.myproject.service.impl;

import com.myproject.model.dto.request.BookingRequestDto;
import com.myproject.model.dto.response.BookingResponseDto;
import com.myproject.model.enums.BookingStatus;
import com.myproject.model.mapper.BookingMapper;
import com.myproject.repository.BookingRepository;
import com.myproject.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;


    @Override
    public BookingResponseDto save(BookingRequestDto dto) {
        var booking = bookingMapper.toEntity(dto);
        booking.setBookingStatus(BookingStatus.INITIATED);
        var savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }
}
