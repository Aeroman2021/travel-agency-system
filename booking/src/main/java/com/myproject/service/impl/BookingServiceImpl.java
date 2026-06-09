package com.myproject.service.impl;

import com.myproject.application.AuthFacade;
import com.myproject.application.flightcabinfacade.FlightCabinFacade;
import com.myproject.event.BookingInitiatedEvent;
import com.myproject.event.compensationevents.CancelBookingEvent;
import com.myproject.event.progressevents.SagaBookingInitiatedEvent;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.request.BookingRequestDto;
import com.myproject.model.dto.response.BookingResponseDto;
import com.myproject.model.entity.Booking;
import com.myproject.model.enums.BookingStatus;
import com.myproject.model.mapper.BookingMapper;
import com.myproject.repository.BookingRepository;
import com.myproject.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final FlightCabinFacade flightCabinFacade;
    private final ApplicationEventPublisher eventPublisher;
    private final AuthFacade authFacade;

    @Override
    @Transactional
    public BookingResponseDto save(BookingRequestDto dto) {

        var booking = bookingMapper.toEntity(dto);

        var totalPrice = flightCabinFacade.calculateTotalPrice(
                dto.flightId(),
                dto.cabinClass(),
                dto.numberOfPassengers()
        );
        booking.setTotalPrice(totalPrice);

        var flightCabin = flightCabinFacade.findByFlightIdAndCabinClass(dto.flightId(),dto.cabinClass());
        booking.setFlightCabinId(flightCabin.getId());

        booking.setCurrentUserId(authFacade.getCurrentUser());

        booking.setBookingStatus(BookingStatus.INITIATED);
        booking.setCurrencyCode("EU");
        var savedBooking = bookingRepository.save(booking);

        eventPublisher.publishEvent(new BookingInitiatedEvent(
                savedBooking.getId(),
                savedBooking.getFlightId(),
                savedBooking.getFlightCabinId(),
                savedBooking.getNumberOfPassengers()));

        eventPublisher.publishEvent(new SagaBookingInitiatedEvent(
                savedBooking.getId(),
                savedBooking.getFlightId(),
                savedBooking.getNumberOfPassengers()));

        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public void cancelBookingEvent(CancelBookingEvent event) {
        var booking = getById(event.bookingId());
        booking.setBookingStatus(BookingStatus.CANCELLED);
    }

    public Booking getById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with id %d not found"
                        .formatted("Booking", bookingId)));

    }

}
