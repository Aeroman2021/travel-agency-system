package com.myproject.service.impl;

import com.myproject.config.KeycloakProperties;
import com.myproject.dto.LoginRequestDto;
import com.myproject.dto.RefreshTokenDto;
import com.myproject.dto.RegisterRequestDto;
import com.myproject.dto.TokenResponse;
import com.myproject.exception.RefreshTokenException;
import com.myproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@EnableConfigurationProperties(KeycloakProperties.class)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestClient restClient = RestClient.create();
    private final KeycloakProperties keycloakProperties;

    @Override
    public TokenResponse login(LoginRequestDto request) {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("client_id", "travel-agency-client");
        body.add("grant_type", "password");
        body.add("username", request.username());
        body.add("password", request.password());

        try {
            return restClient
                    .post()
                    .uri("http://127.0.0.1:8180/realms/travel-agency/protocol/openid-connect/token")
                    .body(body)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .retrieve()
                    .body(TokenResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public void register(RegisterRequestDto requestDto) {
        var token = getAdminToken();

        Map<String, Object> user = new HashMap<>();
        user.put("username", requestDto.username());
        user.put("email", requestDto.email());
        user.put("firstName", requestDto.firstName());
        user.put("lastName", requestDto.lastName());
        user.put("enabled", true);

        Map<String, Object> credential = new HashMap<>();
        credential.put("type", "password");
        credential.put("value", requestDto.password());
        credential.put("temporary", false);
        user.put("credentials", List.of(credential));

        restClient
                .post()
                .uri("http://127.0.0.1:8180/admin/realms/travel-agency/users")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
        return jwt.getSubject();
    }

    private String getAdminToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("client_id", "admin-cli");
        body.add("username", "admin");
        body.add("password", "456789");
        body.add("grant_type", "password");

        TokenResponse response = restClient
                .post()
                .uri("http://127.0.0.1:8180/realms/master/protocol/openid-connect/token")
                .body(body)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .body(TokenResponse.class);

        return response.access_token();
    }

    @Override
    public TokenResponse refresh(RefreshTokenDto refreshTokenDto) {
        MultiValueMap<String,String> form = new LinkedMultiValueMap<>();
        form.add("grant_type","refresh_token");
        form.add("client_id", "travel-agency-client");
        form.add("refresh_token", refreshTokenDto.refreshToken());

        log.info("Token URL = {}", keycloakProperties.getTokenUrl());

        try{
            return restClient.post()
                    .uri("http://127.0.0.1:8180/realms/travel-agency/protocol/openid-connect/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(form)
                    .retrieve()
                    .body(TokenResponse.class);
        }catch (Exception e){
            log.error("Refresh  token failed",e);
            throw new RefreshTokenException("Failed to refresh token",e);
        }
    }
}
