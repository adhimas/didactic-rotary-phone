package com.example.demo.slot;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Converter
public class TimeRangeConverter implements AttributeConverter<TimeRange, String> {
    @Override
    public String convertToDatabaseColumn(TimeRange timeRange) {
        // square bracket = inclusive, bracket = exclusive
        // e.g., "[2010-01-01 14:00, 2010-01-01 15:00)"
        // https://www.postgresql.org/docs/current/rangetypes.html#RANGETYPES-INCLUSIVITY
        return "%s%s,%s%s".formatted(
                "[",
                timeRange.start(),
                timeRange.end(),
                ")"
        );
    }

    @Override
    public TimeRange convertToEntityAttribute(String dbData) {
        // e.g., "[\"2010-01-01 15:00:00+01\",\"2010-01-01 16:00:00+01\")"
        String range = dbData.substring(1, dbData.length() - 1);
        Instant[] timestamps = Arrays.stream(range.split(","))
                .map(s -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ssX");
                    return OffsetDateTime.parse(
                            s.replace("\"", ""),
                            formatter
                    ).toInstant();
                })
                .toArray(Instant[]::new);
        return new TimeRange(timestamps[0], timestamps[1]);
    }
}
