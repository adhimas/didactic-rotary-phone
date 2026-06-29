package com.example.demo.slot;

import com.example.demo.slot.dto.SlotRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SlotServiceTest {

    @Mock
    private SlotRepository slotRepository;

    @InjectMocks
    private SlotService slotService;

    @Test
    void shouldCreateTimeRangeWithProperBounds() {
        ArgumentCaptor<Slot> arg = ArgumentCaptor.forClass(Slot.class);

        var start = ZonedDateTime.of(2026, 12, 31, 22, 0, 0, 0, ZoneId.of("UTC"));
        var end = ZonedDateTime.of(2026, 12, 31, 23, 0, 0, 0, ZoneId.of("UTC"));

        when(slotRepository.save(any())).thenReturn(any());
        slotService.createSlot(123, new SlotRequest(start, end));

        verify(slotRepository).save(arg.capture());
        assertTrue(arg.getValue().getStartEnd().isLowerBoundClosed());
        assertFalse(arg.getValue().getStartEnd().isUpperBoundClosed());
    }
}