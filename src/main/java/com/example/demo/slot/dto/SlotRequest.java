package com.example.demo.slot.dto;

import java.time.ZonedDateTime;

public record SlotRequest(
        ZonedDateTime start,
        ZonedDateTime end
) {}

