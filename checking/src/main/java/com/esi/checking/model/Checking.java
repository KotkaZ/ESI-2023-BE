package com.esi.checking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "support")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checking {
    @Id
    private Integer checkInId;
    private Integer bookingId;
    private Integer roomId;
    private OffsetDateTime checkInAt;
    private OffsetDateTime checkOutAt;
    private String code;
}


