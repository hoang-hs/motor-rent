package com.example.rentcar.present.request;

import com.example.rentcar.core.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequest {
    @NotBlank
    Status status;
}
