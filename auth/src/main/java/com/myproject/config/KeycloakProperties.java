package com.myproject.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@ConfigurationProperties(prefix = "keycloak")
@Component
public class KeycloakProperties {
    private String tokenUrl;
    private String clientId;

    @PostConstruct
    public void init() {
        System.out.println("ClientId = " + clientId);
        System.out.println("TokenUrl = " + tokenUrl);
    }
}
