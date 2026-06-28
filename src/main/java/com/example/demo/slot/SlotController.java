package com.example.demo.slot;

import com.example.demo.slot.dto.SlotRequest;
import com.example.demo.slot.dto.SlotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/{uid}/slots")
public class SlotController {
    private final SlotService service;

    @GetMapping("/")
    public List<SlotResponse> findAll() {
        List<Slot> slots = service.findAll();
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
}
