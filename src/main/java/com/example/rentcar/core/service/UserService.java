package com.example.rentcar.core.service;

import com.example.rentcar.core.domain.User;
import com.example.rentcar.core.domain.repository.UserRepository;
import com.example.rentcar.exception.BadRequestException;
import com.example.rentcar.exception.ResourceNotFoundException;
import com.example.rentcar.present.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByUsernameAndRole(req.getUsername(), req.getRole());
        if (optionalUser.isPresent()) {
            throw BadRequestException.WithMessage("username already exist");
        }
        String password = passwordEncoder.encode(req.getPassword());

        User user = User.builder()
                .password(password)
                .username(req.getUsername())
                .role(req.getRole())
                .email(req.getEmail())
                .number(req.getNumber()).build();
        return userRepository.save(user);
    }

    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::Default);
    }

    public User get() {
        String userId = MDC.get("user_id");
        return userRepository.findById(userId)
                .orElseThrow(ResourceNotFoundException::Default);
    }

}
