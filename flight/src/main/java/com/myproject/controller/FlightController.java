package com.myproject.controller;


import com.myproject.model.dto.response.FlightResponseDto;
import com.myproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<Page<FlightResponseDto>> getAll(@RequestParam (defaultValue = "0")String pageNumber,
                                                          @RequestParam(defaultValue = "10") String pageSize){
        var flights = flightService.getAll(pageNumber,pageSize);
        return new ResponseEntity<>(flights, HttpStatus.ACCEPTED);
    }
}
