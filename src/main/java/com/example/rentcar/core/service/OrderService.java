package com.example.rentcar.core.service;

import com.example.rentcar.core.domain.Motor;
import com.example.rentcar.core.domain.Order;
import com.example.rentcar.core.domain.User;
import com.example.rentcar.core.domain.repository.MotorRepository;
import com.example.rentcar.core.domain.repository.OrderRepository;
import com.example.rentcar.core.domain.repository.UserRepository;
import com.example.rentcar.core.enums.Status;
import com.example.rentcar.exception.BadRequestException;
import com.example.rentcar.exception.ResourceNotFoundException;
import com.example.rentcar.exception.SystemErrorException;
import com.example.rentcar.present.request.CreateOrderRequest;
import com.example.rentcar.present.request.UpdateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.example.rentcar.core.service.DateService.dateLocalToUTC;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MotorRepository motorRepository;
    private final UserRepository userRepository;

    public Order CreateOrder(CreateOrderRequest req) {
        Motor motor = motorRepository.findByType(req.getType()).
                orElseThrow(ResourceNotFoundException::Default);
        List<Order> orders = orderRepository.findAllByStatusAndMotorAndDateOrder(Status.SUCCESS, motor, dateLocalToUTC(req.getDate()));

        int count = orders.stream().mapToInt(Order::getNumber).sum();
        if ((count + req.getNumber()) >= motor.getTotal()) {
            throw BadRequestException.WithMessage("not enough");
        }
        String userId = MDC.get("user_id");
        User user = userRepository.findById(userId).
                orElseThrow(SystemErrorException::Default);
        Order order = new Order(user, motor, req.getNumber(), req.getDate());
        return orderRepository.save(order);
    }

    public Order updateOrder(String id, UpdateOrderRequest req) {
        Order order = orderRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::Default);
        order.setStatus(req.getStatus());
        order.setUpdatedAt(Date.from(Instant.now()));
        return orderRepository.save(order);
    }

}
