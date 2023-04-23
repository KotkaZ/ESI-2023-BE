package com.esi.bookings.mapper;

import com.esi.bookings.model.Booking;
import com.esi.bookings.models.BookingCreateDto;
import com.esi.bookings.models.BookingDto;
import com.esi.events.BookingEvent;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingsMapper {

    BookingDto entityToDto(Booking booking);

    Booking dtoToEntity(BookingDto bookingDto);

    List<BookingDto> entitiesToDtoList(List<Booking> bookings);

    List<Booking> dtoListToEntities(List<BookingDto> bookingDtoList);

    Booking bookingCreateDtoToEntity(BookingCreateDto bookingCreateDto);

    @Mapping(target = "status", source = "status")
    BookingEvent mapToEvent(Booking booking);

}
