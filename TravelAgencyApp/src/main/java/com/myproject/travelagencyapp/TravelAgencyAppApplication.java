package com.myproject.travelagencyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.myproject")
@EntityScan(basePackages = "com.myproject.model.entity")
@SpringBootApplication(scanBasePackages = "com.myproject")
public class TravelAgencyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyAppApplication.class, args);
    }

}
