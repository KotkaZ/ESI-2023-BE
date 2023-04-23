package com.esi.support.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckingEvent {

    private Integer Id;

    private Integer bookingId;

    private Integer roomId;

    private LocalDate checkInAt;

    private LocalDate checkOutAt;

    private String code;

}

