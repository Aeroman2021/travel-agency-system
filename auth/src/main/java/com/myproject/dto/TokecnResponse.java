package com.myproject.dto;

public record TokecnResponse(
        String access_token,
        int expires_in,
        String refresh_token
) {
}
