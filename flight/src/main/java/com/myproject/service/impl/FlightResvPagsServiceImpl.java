package com.myproject.service.impl;

import com.myproject.model.dto.response.FlightResvPasgsResponseDto;
import com.myproject.model.event.FlightReservedEvent;
import com.myproject.repository.FlightResvPsgrsRepository;
import com.myproject.service.FlightResvPsgrsService;
import org.springframework.stereotype.Service;

@Service
public class FlightResvPagsServiceImpl implements FlightResvPsgrsService {

    private FlightResvPsgrsRepository  flightResvPsgrsRepository;

    @Override
    public FlightResvPasgsResponseDto save(FlightReservedEvent event) {
        return null;
    }
}
