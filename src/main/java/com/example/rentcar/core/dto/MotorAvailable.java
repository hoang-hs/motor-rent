package com.example.rentcar.core.dto;

import com.example.rentcar.core.domain.Motor;
import lombok.Data;

@Data
public class MotorAvailable {
    public Motor motor;
    public Integer available;
}
