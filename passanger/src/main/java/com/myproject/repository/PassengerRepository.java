package com.myproject.repository;

import com.myproject.model.dto.PassengerResponseDto;
import com.myproject.model.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    List<PassengerResponseDto> getPassengersByBookingId(Long bookingId);

    List<Passenger> findPassengersByBookingId(Long bookingId);
}
