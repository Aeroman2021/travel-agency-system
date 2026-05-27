package com.myproject.listener;

import com.myproject.model.dto.request.BookingSagaRequestDto;
import com.myproject.model.event.BookingInitiatedEvent;
import com.myproject.service.BookingSagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingInitiatedListener {

    private final BookingSagaService bookingSagaService;

    @EventListener
    public void handleBookingInitiated(BookingInitiatedEvent event) {
        log.info("Received Booking  event: {}", event.bookingId());

        bookingSagaService.startSaga(new BookingSagaRequestDto(event.bookingId()));
    }
}
