package com.tijanidevit.phylx.common.mapper;

import org.springframework.stereotype.Component;

import com.tijanidevit.phylx.user.entity.User;
import com.tijanidevit.phylx.user.response.UserResponse;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
