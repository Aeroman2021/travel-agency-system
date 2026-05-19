package com.myproject.service.impl;

import com.myproject.application.FlightFacade;
import com.myproject.model.dto.request.BookingRequestDto;
import com.myproject.model.dto.response.BookingResponseDto;
import com.myproject.model.enums.BookingStatus;
import com.myproject.model.event.BookingInitiatedEvent;
import com.myproject.model.mapper.BookingMapper;
import com.myproject.repository.BookingRepository;
import com.myproject.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final FlightFacade flightFacade;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public BookingResponseDto save(BookingRequestDto dto) {

        var booking = bookingMapper.toEntity(dto);

        var flightPrice = flightFacade.getFlightPriceByFlightId(dto.getFlightId()).price();
        var totalPrice = flightPrice.multiply(BigDecimal.valueOf(dto.getNumberOfPassengers()));

        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus(BookingStatus.INITIATED);

        var savedBooking = bookingRepository.save(booking);

        eventPublisher.publishEvent(new BookingInitiatedEvent(savedBooking.getId(),
                savedBooking.getFlightId(),
                savedBooking.getNumberOfPassengers()));

        return bookingMapper.toDto(savedBooking);
    }
}
