package com.example.rentcar.present.controller;

import com.example.rentcar.core.domain.User;
import com.example.rentcar.core.service.UserService;
import com.example.rentcar.present.request.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    User update(@PathVariable String id,
                @RequestBody @Valid LoginRequest req) {
        return userService.update(id, req);
    }

}
