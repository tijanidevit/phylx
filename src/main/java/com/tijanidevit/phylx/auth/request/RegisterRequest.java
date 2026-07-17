package com.tijanidevit.phylx.auth.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    
    @NotNull(message = "Name is required")
    @Length(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password is required")
    @Length(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
