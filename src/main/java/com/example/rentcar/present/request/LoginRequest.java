package com.example.rentcar.present.request;

import com.example.rentcar.core.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotNull
    Role role;

    String email;
    String number;
}

