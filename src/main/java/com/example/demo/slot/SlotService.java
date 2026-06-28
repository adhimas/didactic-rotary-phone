package com.example.demo.slot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotService {
    private final SlotRepository repository;

    public List<Slot> findAll() {
        return repository.findAll();
    }
}
