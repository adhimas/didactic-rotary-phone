package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
}
