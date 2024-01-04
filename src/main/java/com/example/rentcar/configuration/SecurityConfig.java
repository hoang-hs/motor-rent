package com.example.rentcar.configuration;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecretKey createKey() {
        String secretKey = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
//        SecretKey secretKey1 = Keys.hmacShaKeyFor(keyBytes);
//        httpSecurity.authorizeHttpRequests((auth) -> auth.requestMatchers("/auth/**")
//                        .permitAll().anyRequest().authenticated())
//                .addFilterAfter(new AuthenticationFilter(secretKey1), UsernamePasswordAuthenticationFilter.class).
//                csrf(AbstractHttpConfigurer::disable).
//                cors((httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configure(httpSecurity)));
//        return httpSecurity.build();
//    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/auth/**");
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//        return http.build();
//    }
}