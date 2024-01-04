package com.example.rentcar.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrResource {

    @JsonProperty("http_code")
    private int httpCode;

    @JsonProperty("message")
    private String message;

    public ErrResource(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }
}
