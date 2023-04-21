package com.esi.support.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer requestId;
    private Integer bookingId;
    private Integer assignedTo;
    private OffsetDateTime requestedAt;
    private OffsetDateTime cleanedAt;
}
