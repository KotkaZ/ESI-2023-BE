package com.esi.payments.dto;

import com.esi.payments.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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

    private PaymentStatus status;

    private OffsetDateTime changedAt;

}

