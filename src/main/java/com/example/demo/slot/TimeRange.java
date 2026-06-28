package com.example.demo.slot;

import java.time.Instant;

public record TimeRange(
        Instant start,
        Instant end
) {}
