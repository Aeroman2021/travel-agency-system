package com.myproject.enums;

public enum SagaStep {
    BOOKING_CREATED,

    FLIGHT_RESERVED,

    PASSENGERS_REGISTERED,

    PAYMENT_COMPLETED,

    TICKET_ISSUED;


    public  static SagaStep nextStep(SagaStep step){
        return switch (step){
            case BOOKING_CREATED -> FLIGHT_RESERVED;
            case FLIGHT_RESERVED -> PASSENGERS_REGISTERED;
            case PASSENGERS_REGISTERED ->PAYMENT_COMPLETED;
            case PAYMENT_COMPLETED -> TICKET_ISSUED;
            case TICKET_ISSUED -> throw new IllegalStateException(
                    "Saga already completed"
            );
        };
    }
}
