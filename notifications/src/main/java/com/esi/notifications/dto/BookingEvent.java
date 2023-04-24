package com.esi.notifications.dto;

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

    private BookingStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate bookDate;

    private BigDecimal price;

    private Integer checkinId;

    private Integer paymentId;

    private OffsetDateTime modifiedAt;

    private Integer modifiedBy;
}

