package com.myproject.model.entity;

import com.myproject.model.enums.CabinClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "flight_cabins")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class FlightCabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private BigDecimal price;

    private String currencyCode;

    @Enumerated(EnumType.STRING)
    private CabinClass cabinClass;

    private int availableSeats;

}
