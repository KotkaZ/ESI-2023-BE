package com.esi.bookings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckingEvent {

    private Integer id;

    private Integer bookingId;

    private Integer roomId;

    private OffsetDateTime checkInAt;

    private OffsetDateTime checkOutAt;

    private String code;
}

