package com.myproject.repository;

import com.myproject.model.view.FlightView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightViewRepository extends JpaRepository<FlightView,Long> {
}
