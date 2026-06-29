package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    @GetMapping("/")
    public List<User> findAll() {
        return service.findAll();
    }

    // TODO: implement other API for user entity
}
