package com.example.rentcar.present.controller;

import com.example.rentcar.core.domain.Motor;
import com.example.rentcar.core.service.MotorService;
import com.example.rentcar.present.request.AddMotorRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motor")
@RequiredArgsConstructor
public class MotorController {
    private final MotorService motorService;

    @PostMapping("")
    Motor addMotor(@RequestBody @Valid AddMotorRequest req) {
        return motorService.addMotor(req);
    }


}
