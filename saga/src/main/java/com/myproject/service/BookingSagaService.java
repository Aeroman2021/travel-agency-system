package com.myproject.service;

import com.myproject.event.*;

public interface BookingSagaService {
    void handleSagaEvent(SagaEvent sagaEvent);
    void compensateSagaEvent(SagaEvent sagaEvent);
}
