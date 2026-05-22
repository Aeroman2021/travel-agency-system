package com.myproject.service;

import com.myproject.model.dto.response.FlightResvPasgsResponseDto;
import com.myproject.model.event.FlightReservedEvent;
import com.myproject.model.event.TicketSuccessfullyIssuedEvent;

public interface FlightResvPsgrsService {
    FlightResvPasgsResponseDto save(TicketSuccessfullyIssuedEvent event);
}
