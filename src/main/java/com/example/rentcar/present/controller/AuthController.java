package com.example.rentcar.present.controller;

import com.example.rentcar.core.domain.User;
import com.example.rentcar.core.dto.Token;
import com.example.rentcar.core.service.AuthService;
import com.example.rentcar.core.service.UserService;
import com.example.rentcar.present.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    User save(@RequestBody LoginRequest req) {
        return userService.save(req);
    }

    @PostMapping("/login")
    Token login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

}
