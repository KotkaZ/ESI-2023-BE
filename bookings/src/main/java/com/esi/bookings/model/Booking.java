package com.esi.bookings.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer roomId;

    @Enumerated(EnumType.STRING)
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

