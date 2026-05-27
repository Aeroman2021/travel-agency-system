package com.myproject.service;

import com.myproject.model.enums.SagaStep;
import org.springframework.stereotype.Component;

import static com.myproject.model.enums.SagaStep.*;

@Component
public class SagaTransitionPolicy {

    public   SagaStep nextStep(SagaStep step){
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
