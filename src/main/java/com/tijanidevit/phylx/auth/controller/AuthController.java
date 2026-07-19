package com.tijanidevit.phylx.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tijanidevit.phylx.auth.request.LoginRequest;
import com.tijanidevit.phylx.auth.request.RegisterRequest;
import com.tijanidevit.phylx.auth.response.AuthResponse;
import com.tijanidevit.phylx.auth.service.AuthService;
import com.tijanidevit.phylx.common.controller.BaseController;
import com.tijanidevit.phylx.common.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController extends BaseController{


    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        
        return created("User registered successfully", authService.register(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        return success("Login successful", authService.login(request));
    }
    
}
