package com.example.demo.slot;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "slots")
public class Slot {
    @Id
    private int id;

    // private int uid;
    // private int mid;

    @Column(columnDefinition = "tstzrange")
    @Convert(converter = TimeRangeConverter.class)
    private TimeRange startEnd;
}
