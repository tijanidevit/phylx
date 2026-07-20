package com.tijanidevit.phylx.auth.service;

import com.tijanidevit.phylx.common.mapper.UserMapper;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tijanidevit.phylx.auth.entity.EmailVerificationToken;
import com.tijanidevit.phylx.auth.repository.EmailVerificationTokenRepository;
import com.tijanidevit.phylx.auth.request.LoginRequest;
import com.tijanidevit.phylx.auth.request.RegisterRequest;
import com.tijanidevit.phylx.auth.response.AuthResponse;
import com.tijanidevit.phylx.common.exception.BusinessRuleException;
import com.tijanidevit.phylx.common.mail.EmailService;
import com.tijanidevit.phylx.common.security.JwtService;
import com.tijanidevit.phylx.common.util.TokenGenerator;
import com.tijanidevit.phylx.user.entity.User;
import com.tijanidevit.phylx.user.enums.Role;
import com.tijanidevit.phylx.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;


    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessRuleException(
                    "A user with this email address already exists.",
                    HttpStatus.CONFLICT
            );
        }
        
        var user = User.builder()
                        .email(request.getEmail())
                        .name(request.getName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .build();
        userRepository.save(user);

        createEmailAndSendToken(user);

        return buildResponse(user);
    }

    private void createEmailAndSendToken(User user) {
        String token = TokenGenerator.numericCode(6);

        EmailVerificationToken verificationToken = emailVerificationTokenRepository
            .findByUserId(user.getId())
            .orElseGet(() -> EmailVerificationToken.builder()
                    .userId(user.getId())
                    .build());

        verificationToken.setToken(token);
        verificationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));

        emailService.sendVerificationEmail(user, token);
    }

    public AuthResponse login(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        var user = (User) authentication.getPrincipal();
        return buildResponse(user);
    }


    private AuthResponse buildResponse(User user) {
        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .user(userMapper.toResponse(user))
                .build();
    }
}
