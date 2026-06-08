package com.myproject.service.impl;

import com.myproject.model.dto.response.FlightViewDto;
import com.myproject.model.enums.CabinClass;
import com.myproject.model.maper.FlightViewMapper;
import com.myproject.model.view.FlightView;
import com.myproject.repository.FlightViewRepository;
import com.myproject.service.FlightViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FlightViewServiceImpl implements FlightViewService {

    private final FlightViewRepository  flightViewRepository;
    private final FlightViewMapper flightViewMapper;

    @Override
    public Page<FlightViewDto> getAll(String pageNumber, String pageSize) {
        var pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));

        List<FlightViewDto> list = new ArrayList<>();
        for (FlightView flightView : flightViewRepository.findAll(pageable)) {
           flightView.getAvailableCabins().split(",");
            List<CabinClass> cabinClassList = Arrays.stream(flightView.getAvailableCabins().split(","))
                    .map(CabinClass::valueOf)
                    .toList();
            FlightViewDto dto = flightViewMapper.toDto(flightView);
            dto.setAvailableCabins(cabinClassList);
            list.add(dto);
        }

        return new PageImpl<>(list,pageable, list.size());
    }
}
