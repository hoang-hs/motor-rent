package com.example.rentcar.present.controller;

import com.example.rentcar.core.domain.Order;
import com.example.rentcar.core.enums.Status;
import com.example.rentcar.core.service.OrderService;
import com.example.rentcar.present.request.CreateOrderRequest;
import com.example.rentcar.present.request.UpdateOrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    Order createOrder(@RequestBody @Valid CreateOrderRequest req) {
        return orderService.CreateOrder(req);
    }

    @PutMapping("/{id}")
    Order updateOrder(@PathVariable String id,
                      @RequestBody @Valid UpdateOrderRequest req) {
        return orderService.updateOrder(id, req);
    }

    @GetMapping("")
    List<Order> getOrder(@RequestParam String username,
                         @RequestParam Status status) {
        return orderService.getOrder(username, status);
    }
}
