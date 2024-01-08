package com.example.rentcar.present.controller;

import com.example.rentcar.core.domain.Motor;
import com.example.rentcar.core.dto.MotorAvailable;
import com.example.rentcar.core.service.MotorService;
import com.example.rentcar.present.request.AddMotorRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/motor")
@RequiredArgsConstructor
public class MotorController {
    private final MotorService motorService;

    @PostMapping("")
    Motor addMotor(@RequestBody @Valid AddMotorRequest req) {
        return motorService.addMotor(req);
    }

    @GetMapping("")
    List<MotorAvailable> getMotorAvailable(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return motorService.getMotorAvailable(date);
    }

}
