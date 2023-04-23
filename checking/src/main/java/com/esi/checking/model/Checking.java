package com.esi.checking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "reception")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bookingId;
    private Integer roomId;
    private OffsetDateTime checkInAt;
    private OffsetDateTime checkOutAt;
    private String code;
}


