package com.myproject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.myproject")
@EnableJpaRepositories(basePackages = "com.myproject.repository")
@ConfigurationPropertiesScan(basePackages = {"com.myproject.config","com.myproject"})
@RequiredArgsConstructor
public class TravelAgencyAppApplication  {
    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyAppApplication.class, args);
    }


}
