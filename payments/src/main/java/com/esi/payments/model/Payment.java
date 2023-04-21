package com.esi.payments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "payments")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private Integer bookingId;
    private Integer userId;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private OffsetDateTime changedAt;
}




