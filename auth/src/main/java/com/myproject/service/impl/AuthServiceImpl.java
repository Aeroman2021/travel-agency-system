package com.myproject.service.impl;

import com.myproject.dto.LoginRequestDto;
import com.myproject.dto.RegisterRequestDto;
import com.myproject.dto.TokecnResponse;
import com.myproject.service.AuthService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private final RestClient restClient = RestClient.create();

    @Override
    public TokecnResponse login(LoginRequestDto request) {

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
                    .body(TokecnResponse.class);

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

    private String getAdminToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("client_id", "admin-cli");
        body.add("username", "admin");
        body.add("password", "456789");
        body.add("grant_type", "password");

        TokecnResponse response = restClient
                .post()
                .uri("http://127.0.0.1:8180/realms/master/protocol/openid-connect/token")
                .body(body)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .body(TokecnResponse.class);

        return response.access_token();
    }
}
