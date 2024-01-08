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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.rentcar.core.service.DateService.dateToLocalDateTime;
import static com.example.rentcar.core.service.DateService.localDateTimeToDate;

@Service
@RequiredArgsConstructor
public class MotorService {
    private final MotorRepository motorRepository;
    private final OrderRepository orderRepository;

    public Motor addMotor(AddMotorRequest req) {
        Optional<Motor> optionalMotor = motorRepository.findByType(req.getType());
        if (optionalMotor.isPresent()) {
            Motor motor = optionalMotor.get();
            motor.setTotal(motor.getTotal() + req.getNumber());
            motor.setUpdatedAt(Date.from(Instant.now()));
            motorRepository.save(motor);
            return motor;
        }
        Motor motor = new Motor();
        motor.setType(req.getType());
        motor.setTotal(req.getNumber());
        motorRepository.save(motor);
        return motor;
    }

    public List<MotorAvailable> getMotorAvailable(Date date) {
        List<Motor> motors = motorRepository.findAll();
        List<MotorAvailable> motorAvailables = new ArrayList<>();

        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        Date fromDate = localDateTimeToDate(startOfDay);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        Date todate = localDateTimeToDate(endOfDay);

        for (Motor motor : motors) {
            List<Order> orders = orderRepository.findAllByStatusAndMotorAndUpdatedAtBetween(Status.SUCCESS, motor, todate, fromDate);

            int count = orders.stream().mapToInt(Order::getNumber).sum();
            if (count >= motor.getTotal()) {
                break;
            }
            MotorAvailable motorAvailable = new MotorAvailable();
            motorAvailable.setMotor(motor);
            motorAvailable.setAvailable(motor.getTotal() - count);
            motorAvailables.add(motorAvailable);
        }
        return motorAvailables;
    }


}
