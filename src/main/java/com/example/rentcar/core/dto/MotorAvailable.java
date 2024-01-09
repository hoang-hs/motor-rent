package com.example.rentcar.core.dto;

import com.example.rentcar.core.domain.Motor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MotorAvailable {
    public Motor motor;
    public Integer available;
}
