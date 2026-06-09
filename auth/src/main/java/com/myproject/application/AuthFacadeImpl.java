package com.myproject.application;

import com.myproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade{

    private final AuthService authService;
    @Override
    public String getCurrentUser() {
        return authService.getCurrentUser();
    }
}
