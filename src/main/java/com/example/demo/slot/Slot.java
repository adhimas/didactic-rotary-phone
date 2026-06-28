package com.example.demo.slot;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "slots")
public class Slot {
    @Id
    private Integer id;

    private Integer uid;
    private Integer mid;

    // custom type for PostgreSQL range type:
    // https://github.com/vladmihalcea/hypersistence-utils
    @Column(columnDefinition = "tstzrange")
    @Type(PostgreSQLRangeType.class)
    private Range<ZonedDateTime> startEnd;
}
