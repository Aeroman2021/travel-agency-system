package com.myproject.service;

import com.myproject.dto.LoginRequestDto;
import com.myproject.dto.TokecnResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
public class AuthService {
    private final RestClient restClient = RestClient.create();

    public TokecnResponse login(LoginRequestDto request) {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("client_id", "travel-agency-client");
        body.add("grant_type", "password");
        body.add("username", request.username());
        body.add("password", request.password());

        try{
            return restClient
                    .post()
                    .uri("http://127.0.0.1:8180/realms/travel-agency/protocol/openid-connect/token")
                    .body(body)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .retrieve()
                    .body(TokecnResponse.class);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
