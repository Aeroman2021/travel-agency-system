package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.myproject")
@EnableJpaRepositories(basePackages = "com.myproject.repository")
public class TravelAgencyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyAppApplication.class, args);
    }

}
