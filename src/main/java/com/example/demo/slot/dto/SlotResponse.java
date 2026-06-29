package com.example.demo.slot.dto;

import java.time.ZonedDateTime;
import java.util.Optional;

public record SlotResponse(
        ZonedDateTime start,
        ZonedDateTime end,
        int id,
        int userId,
        Optional<Integer> eventId // e.g., a meeting
) {}
