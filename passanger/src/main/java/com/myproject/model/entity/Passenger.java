package com.myproject.model.entity;

import com.myproject.model.enums.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "passengers")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    private String ncode;

    @Column(name = "passport_number")
    private String passportNumber;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "date_of_birth")
    private LocalDate dob;

}
