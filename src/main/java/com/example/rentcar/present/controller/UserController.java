package com.example.rentcar.present.controller;

import com.example.rentcar.core.domain.User;
import com.example.rentcar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService userService;

    @GetMapping("/{id}")
    User getById(@PathVariable String id) {
        return userService.getById(id);
    }

    @GetMapping("")
    User get() {
        return userService.get();
    }

}
