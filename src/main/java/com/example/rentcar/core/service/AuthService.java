package com.example.rentcar.core.service;


import com.example.rentcar.core.domain.User;
import com.example.rentcar.core.domain.repository.UserRepository;
import com.example.rentcar.core.dto.Token;
import com.example.rentcar.exception.UnauthorizedException;
import com.example.rentcar.present.request.LoginRequest;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;

    public Token login(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername()).
                orElseThrow(() -> UnauthorizedException.WithMessage("username incorrect"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw UnauthorizedException.WithMessage("password incorrect");
        }
        Date now = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        int expireTime = 30 * 24;
        cl.add(Calendar.HOUR, expireTime);


        String token = Jwts.builder()
                .subject(user.getId())
                .issuedAt(now)
                .expiration(cl.getTime())
                .signWith(secretKey)
                .compact();
        return new Token(token, expireTime * 60);
    }

}
