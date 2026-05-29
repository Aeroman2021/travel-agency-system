package com.myproject.listener;

import com.myproject.event.compensationevents.CancelPassengerEvent;
import com.myproject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelPassengerListener {

    private final PassengerService  passengerService;

    @EventListener
    public void handle(CancelPassengerEvent event){
        passengerService.cancelPassenger(event);
    }
}
