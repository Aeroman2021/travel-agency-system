package com.myproject.event;

public record SagaPassengerRegisteredEvent(Long bookingId
) implements SagaEvent{

}
