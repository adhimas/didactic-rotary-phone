package com.example.demo.slot.dto;

import java.time.ZonedDateTime;

public record MeetingRequest(
        String title,
        String description
) {}
