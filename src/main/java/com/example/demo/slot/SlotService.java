package com.example.demo.slot;

import com.example.demo.slot.dto.SlotRequest;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SlotService {
    private final SlotRepository repository;
    private final MeetingRepository meetingRepository;

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

    public List<Slot> findUserSlots(int id) {
        return repository.findByUser(id);
    }

    public List<Slot> findUserSlotsByRange(int id, Range<ZonedDateTime> range) {
        return repository.findByUserAndRange(id, range.asString());
    }

    @Transactional
    public Slot createMeeting(int slotId, String title) {
        Slot slot = repository.findById(slotId).orElseThrow();

        int meetingId = ThreadLocalRandom.current().nextInt(0, 100);
        Meeting meeting = new Meeting();
        meeting.setId(meetingId);
        meeting.setTitle(title);
        meetingRepository.save(meeting);

        slot.setMid(meetingId);
        repository.save(slot);

        return slot;
    }
}
