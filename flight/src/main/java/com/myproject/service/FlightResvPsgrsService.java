package com.myproject.service;

import com.myproject.model.dto.response.FlightResvPasgsResponseDto;
import com.myproject.model.event.FlightReservedEvent;

public interface FlightResvPsgrsService {
    FlightResvPasgsResponseDto save(FlightReservedEvent event);
}
