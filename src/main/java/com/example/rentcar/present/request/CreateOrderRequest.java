package com.example.rentcar.present.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class CreateOrderRequest {
    @NotBlank
    String type;

    @Min(1)
    Integer number;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date;
}
