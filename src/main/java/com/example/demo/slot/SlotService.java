package com.example.demo.slot;

import com.example.demo.slot.dto.SlotRequest;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SlotService {
    private final SlotRepository repository;
    private final MeetingRepository meetingRepository;

    public Slot createSlot(int uid, SlotRequest request) {
        // TODO: update to more scalable IDs
        int id = ThreadLocalRandom.current().nextInt(0, 100);

        Slot slot = new Slot();
        slot.setId(id);
        slot.setUid(uid);

        // set range to have inclusive start point and exclusive end point
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
        // TODO: handle error
        Slot slot = repository.findById(slotId).orElseThrow();

        // TODO: update to more scalable IDs
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
