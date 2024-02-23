package com.example.rentcar.present.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMotorRequest {
    @NotNull
    String type;
    @Min(1)
    Integer number;
    @Min(1)
    Long price;
}
