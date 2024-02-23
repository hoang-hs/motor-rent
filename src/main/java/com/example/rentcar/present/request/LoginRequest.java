package com.example.rentcar.present.request;

import com.example.rentcar.core.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    String username;
    String password;
    Role role;

    String name;
    String email;
    String number;
}

