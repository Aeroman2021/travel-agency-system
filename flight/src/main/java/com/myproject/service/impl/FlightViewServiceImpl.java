package com.myproject.service.impl;

import com.myproject.model.view.FlightView;
import com.myproject.repository.FlightViewRepository;
import com.myproject.service.FlightViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FlightViewServiceImpl implements FlightViewService {

    private final FlightViewRepository  flightViewRepository;

    @Override
    public Page<FlightView> getAll(String pageNumber, String pageSize) {
        var pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        var flights = flightViewRepository.findAll(pageable)
                .stream().toList();
        return new PageImpl<>(flights,pageable, flights.size());
    }
}
