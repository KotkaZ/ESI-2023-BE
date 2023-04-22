package com.esi.bookings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent {

    private Integer Id;

    private Integer bookingId;

    private Integer userId;

    private BigDecimal price;

    private BigDecimal totalAmount;

    private LocalDate checkInAt;

    private LocalDate changedAt;

}

