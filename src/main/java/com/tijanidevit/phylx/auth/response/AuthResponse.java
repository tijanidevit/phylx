package com.tijanidevit.phylx.auth.response;

import com.tijanidevit.phylx.user.response.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private UserResponse user;
}
