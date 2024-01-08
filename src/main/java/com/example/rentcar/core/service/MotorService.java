package com.example.rentcar.core.service;

import com.example.rentcar.core.domain.Motor;
import com.example.rentcar.core.domain.repository.MotorRepository;
import com.example.rentcar.present.request.AddMotorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotorService {
    private final MotorRepository motorRepository;

    public Motor addMotor(AddMotorRequest req) {
        Optional<Motor> optionalMotor = motorRepository.findByType(req.getType());
        if (optionalMotor.isPresent()) {
            Motor motor = optionalMotor.get();
            motor.setTotal(motor.getTotal() + req.getNumber());
            motor.setUpdatedAt(Instant.now());
            motorRepository.save(motor);
            return motor;
        }
        Motor motor = new Motor();
        motor.setType(req.getType());
        motor.setTotal(req.getNumber());
        motorRepository.save(motor);
        return motor;
    }

}
