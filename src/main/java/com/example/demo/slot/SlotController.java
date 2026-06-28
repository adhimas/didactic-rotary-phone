package com.example.demo.slot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/{id}/slots")
public class SlotController {
    private final SlotService service;

    @GetMapping("/")
    public List<Slot> findAll() {
        return service.findAll();
    }
}
