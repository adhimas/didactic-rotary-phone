package com.example.demo.slot;

import com.example.demo.slot.dto.MeetingRequest;
import com.example.demo.slot.dto.SlotRequest;
import com.example.demo.slot.dto.SlotResponse;
import io.hypersistence.utils.hibernate.type.range.Range;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/{uid}/slots")
// TODO: validate request
// TODO: handle error
public class SlotController {
    private final SlotService service;

    @GetMapping
    public List<SlotResponse> findAll(
            @PathVariable int uid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd
    ) {
        List<Slot> slots;

        if (rangeStart != null && rangeEnd != null) {
            // TODO: test URL-encoded range input strings
            Range<ZonedDateTime> range = Range.closedOpen(
                    ZonedDateTime.parse(rangeStart),
                    ZonedDateTime.parse(rangeEnd)
            );
            slots = service.findUserSlotsByRange(uid, range);
        } else {
            slots = service.findUserSlots(uid);
        }

        return slots.stream().
                map(slot -> new SlotResponse(
                        slot.getStartEnd().lower(),
                        slot.getStartEnd().upper(),
                        slot.getId(),
                        slot.getUid(),
                        Optional.ofNullable(slot.getMid())
                )).
                toList();
    }

    @PostMapping("/")
    public SlotResponse create(@PathVariable int uid, @RequestBody SlotRequest request) {
        Slot slot = service.createSlot(uid, request);
        return new SlotResponse(
                slot.getStartEnd().lower(),
                slot.getStartEnd().upper(),
                slot.getId(),
                slot.getUid(),
                Optional.ofNullable(slot.getMid())
        );
    }

    @PostMapping("/{sid}/meeting")
    public SlotResponse createMeeting(
            @PathVariable int sid,
            @RequestBody MeetingRequest request
    ) {
        Slot slot = service.createMeeting(sid, request.title());
        return new SlotResponse(
                slot.getStartEnd().lower(),
                slot.getStartEnd().upper(),
                slot.getId(),
                slot.getUid(),
                Optional.ofNullable(slot.getMid())
        );
    }

    // TODO: implement other API for slot entity
}
