package com.myproject.listener;

import com.myproject.event.compensationevents.CancelBookingEvent;
import com.myproject.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelBookingListener {

    private final BookingService bookingService;

    @EventListener
    public void handle(CancelBookingEvent event){
        bookingService.cancelBookingEvent(event);
    }
}
