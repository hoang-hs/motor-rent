package com.example.rentcar.core.dto;

import lombok.Data;

@Data
public class Token {
    String token;
    int expireTime;

    public Token(String token, int expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }
}
