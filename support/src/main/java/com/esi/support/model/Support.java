package com.esi.support.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "support")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;
    private Integer bookingId;
    private Integer roomId;
    private Integer assignedTo;
    private OffsetDateTime requestedAt;
    private OffsetDateTime cleaningStartedAt;
    private OffsetDateTime cleanedAt;
}
