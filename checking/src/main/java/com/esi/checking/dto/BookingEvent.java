package com.esi.checking.dto;

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
public class BookingEvent {

    private Integer id;

    private Integer userId;

    private Integer roomId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate bookDate;

    private BigDecimal price;

    private Integer checkinId;

    private Integer paymentId;

    private OffsetDateTime modifiedAt;

    private Integer modifiedBy;
}

