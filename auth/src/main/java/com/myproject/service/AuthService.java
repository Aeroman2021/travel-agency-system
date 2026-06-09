package com.myproject.service;

import com.myproject.dto.LoginRequestDto;
import com.myproject.dto.RegisterRequestDto;
import com.myproject.dto.TokecnResponse;

public interface AuthService {
    TokecnResponse login(LoginRequestDto request);
    void register(RegisterRequestDto requestDto);
    String getCurrentUser();



}
