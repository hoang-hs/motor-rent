package com.example.rentcar.core.service;

import com.example.rentcar.core.domain.Motor;
import com.example.rentcar.core.domain.Order;
import com.example.rentcar.core.domain.repository.MotorRepository;
import com.example.rentcar.core.domain.repository.OrderRepository;
import com.example.rentcar.core.dto.MotorAvailable;
import com.example.rentcar.core.enums.Status;
import com.example.rentcar.present.request.AddMotorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.rentcar.core.service.DateService.dateLocalToUTC;

@Service
@RequiredArgsConstructor
public class MotorService {
    private final MotorRepository motorRepository;
    private final OrderRepository orderRepository;

    public Motor addMotor(AddMotorRequest req) {
        Optional<Motor> optionalMotor = motorRepository.findByType(req.getType());
        if (optionalMotor.isPresent()) {
            Motor motor = optionalMotor.get();
            motor.setPrice(req.getPrice());
            motor.setTotal(motor.getTotal() + req.getNumber());
            motor.setUpdatedAt(Date.from(Instant.now()));
            motorRepository.save(motor);
            return motor;
        }
        Motor motor = Motor.builder()
                .price(req.getPrice())
                .type(req.getType())
                .total(req.getNumber()).build();
        motorRepository.save(motor);
        return motor;
    }

    public List<MotorAvailable> getMotorAvailable(Date date) {
        List<Motor> motors = motorRepository.findAll();
        List<MotorAvailable> motorAvailables = new ArrayList<>();
        for (Motor motor : motors) {
            List<Order> orders = orderRepository.findAllByStatusAndMotorAndDateOrder(Status.SUCCESS, motor, dateLocalToUTC(date));

            int count = orders.stream().mapToInt(Order::getNumber).sum();
            if (count >= motor.getTotal()) {
                break;
            }
            MotorAvailable motorAvailable = MotorAvailable.builder()
                    .motor(motor)
                    .available(motor.getTotal() - count).build();
            motorAvailables.add(motorAvailable);
        }
        return motorAvailables;
    }


}
