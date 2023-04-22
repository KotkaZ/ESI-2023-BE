package com.esi.events;

import java.time.LocalDate;
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

}

