package com.esi.rooms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private Integer roomNumber;
    private String description;
    private BigDecimal price;
    private Integer guestsMaxNumber;
}
