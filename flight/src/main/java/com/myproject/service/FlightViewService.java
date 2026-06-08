package com.myproject.service;

import com.myproject.model.dto.response.FlightViewDto;
import org.springframework.data.domain.Page;

public interface FlightViewService {
    Page<FlightViewDto> getAll(String pageNumber, String pageSize);

}
