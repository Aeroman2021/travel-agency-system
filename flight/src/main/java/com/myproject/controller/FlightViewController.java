package com.myproject.controller;

import com.myproject.model.dto.response.FlightViewDto;
import com.myproject.model.view.FlightView;
import com.myproject.service.FlightViewService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flight-view")
@RequiredArgsConstructor
public class FlightViewController {

    private final FlightViewService flightViewService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<FlightViewDto>> loadAll(
            @RequestParam(defaultValue = "0") String pageNumber,
            @RequestParam(defaultValue = "20") String pageSize
    ){
        var result = flightViewService.getAll(pageNumber,pageSize);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
