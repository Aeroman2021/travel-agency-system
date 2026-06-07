package com.myproject.service;

import com.myproject.model.dto.response.FlightResponseDto;
import com.myproject.model.view.FlightView;
import org.springframework.data.domain.Page;

public interface FlightViewService {
    Page<FlightView> getAll(String pageNumber, String pageSize);

}
