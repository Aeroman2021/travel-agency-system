package com.myproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TokenResponse(

        @JsonProperty("access_token")
        String access_token,

        @JsonProperty("expires_in")
        Integer expires_in,

        @JsonProperty("refresh_token")
        String refresh_token
) {
}
