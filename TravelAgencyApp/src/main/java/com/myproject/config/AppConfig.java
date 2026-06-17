package com.myproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
    public RestClient restClient(){
        return RestClient.create();    }
}
