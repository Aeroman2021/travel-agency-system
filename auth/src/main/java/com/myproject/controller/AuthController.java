package com.myproject.controller;

import com.myproject.dto.LoginRequestDto;
import com.myproject.dto.RegisterRequestDto;
import com.myproject.dto.TokecnResponse;
import com.myproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokecnResponse> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto requestDto){
         authService.register(requestDto);
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .build();
    }

}
