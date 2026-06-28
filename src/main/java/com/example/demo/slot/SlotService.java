package com.example.demo.slot;

import com.example.demo.slot.dto.SlotRequest;
import io.hypersistence.utils.hibernate.type.range.Range;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SlotService {
    private final SlotRepository repository;

    public List<Slot> findAll() {
        return repository.findAll();
    }

    public Slot createSlot(int uid, SlotRequest request) {
        int id = ThreadLocalRandom.current().nextInt(0, 100);

        Slot slot = new Slot();
        slot.setId(id);
        slot.setUid(uid);
        slot.setStartEnd(Range.closedOpen(request.start(), request.end()));

        return repository.save(slot);
    }
}
