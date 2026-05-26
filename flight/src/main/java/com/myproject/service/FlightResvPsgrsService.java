package com.myproject.service;

import com.myproject.model.dto.response.FlightResvPasgsResponseDto;
import com.myproject.event.TicketSuccessfullyIssuedEvent;

import java.util.List;

public interface FlightResvPsgrsService {
    List<FlightResvPasgsResponseDto> save(TicketSuccessfullyIssuedEvent event);
}
