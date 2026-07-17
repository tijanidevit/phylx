package com.tijanidevit.phylx.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tijanidevit.phylx.auth.request.LoginRequest;
import com.tijanidevit.phylx.auth.request.RegisterRequest;
import com.tijanidevit.phylx.auth.response.AuthResponse;
import com.tijanidevit.phylx.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {


    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        
        return ResponseEntity.ok(authService.register(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        
        System.out.println("LOGIN ENDPOINT HIT");
        return ResponseEntity.ok(authService.login(request));
    }
    
}
