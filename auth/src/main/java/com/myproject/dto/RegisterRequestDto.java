package com.myproject.dto;

public record RegisterRequestDto(
        String username,
        String password,
        String email,
        String firstName,
        String lastName
) {
}
