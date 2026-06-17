package com.myproject.service;

import com.myproject.dto.LoginRequestDto;
import com.myproject.dto.RefreshTokenDto;
import com.myproject.dto.RegisterRequestDto;
import com.myproject.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequestDto request);
    void register(RegisterRequestDto requestDto);
    String getCurrentUser();
    TokenResponse refresh(RefreshTokenDto refreshTokenDto);



}
