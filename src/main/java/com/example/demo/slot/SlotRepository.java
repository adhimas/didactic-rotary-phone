package com.example.demo.slot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
    @Query(value = """
SELECT * FROM slots
WHERE uid = :userId
""", nativeQuery = true)
    List<Slot> findByUser(@Param("userId") int userId);

    @Query(value = """
SELECT * FROM slots
WHERE uid = :userId
AND start_end <@ CAST(:range AS tstzrange)
""", nativeQuery = true)
    List<Slot> findByUserAndRange(@Param("userId") int userId, @Param("range") String range);
}
