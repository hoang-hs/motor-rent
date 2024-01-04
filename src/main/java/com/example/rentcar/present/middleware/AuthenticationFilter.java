package com.example.rentcar.present.middleware;

import com.example.rentcar.exception.AppException;
import com.example.rentcar.exception.ErrResource;
import com.example.rentcar.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;

    private final List<String> path = List.of("/auth/login","/auth/register", "/ping");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (path.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        response.setHeader("Content-Type", "application/json");

        if (StringUtils.isEmpty(token)) {
            AppException exception = UnauthorizedException.WithMessage("token is null");
            response.setStatus(exception.getHttpStatus().value());
            ErrResource err = new ErrResource(exception.getHttpStatus().value(), exception.getMessage());
            response.getOutputStream().print(ow.writeValueAsString(err));
            response.flushBuffer();
            return;
        }
        try {
            Jws<Claims> jws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            Claims claims = jws.getPayload();
            String id = claims.getSubject();
            MDC.put("user_id", id);
            filterChain.doFilter(request, response);
        } catch (Exception ignored) {
            AppException exception = UnauthorizedException.Default();
            response.setStatus(exception.getHttpStatus().value());

            ErrResource err = new ErrResource(exception.getHttpStatus().value(), exception.getMessage());
            response.getOutputStream().print(ow.writeValueAsString(err));
            response.flushBuffer();
        }
    }
}
