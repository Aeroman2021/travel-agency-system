package com.myproject.repository;

import com.myproject.model.dto.FlightReservationDto;
import com.myproject.model.dto.response.FlightReservationResponseDto;
import com.myproject.model.entity.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightReservationRepository extends JpaRepository<FlightReservation,Long> {
    FlightReservationResponseDto findByBookingId(Long bookingId);
}
