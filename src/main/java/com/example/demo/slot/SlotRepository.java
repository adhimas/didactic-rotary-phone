package com.example.demo.slot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
    @Query(value = """
SELECT * FROM slots
WHERE uid = :uid
""", nativeQuery = true)
    List<Slot> findByUser(@Param("uid") int uid);

    @Query(value = """
SELECT * FROM slots
WHERE uid = :uid
AND start_end <@ CAST(:range AS tstzrange)
""", nativeQuery = true)
    List<Slot> findByUserAndRange(@Param("uid") int uid, @Param("range") String range);
}
