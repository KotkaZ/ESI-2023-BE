package com.esi.bookings.mapper;

import com.esi.bookings.model.Booking;
import com.esi.bookings.dto.BookingEvent;

import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingEventMapper {

    BookingEvent mapToEvent(Booking booking);
}
