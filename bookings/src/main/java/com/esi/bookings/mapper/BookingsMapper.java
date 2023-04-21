package com.esi.bookings.mapper;

import com.esi.bookings.model.Booking;
import com.esi.bookings.models.BookingCreateDto;
import com.esi.bookings.models.BookingDto;
import com.esi.bookings.models.RoomAvailabilityDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingsMapper {

    BookingDto entityToDto(Booking booking);

    Booking dtoToEntity(BookingDto bookingDto);

    List<BookingDto> entitiesToDtoList(List<Booking> bookings);

    List<Booking> dtoListToEntities(List<BookingDto> bookingDtoList);

    RoomAvailabilityDto entityToRoomAvailabilityDto(Booking booking);

    Booking bookingCreateDtoToEntity (BookingCreateDto bookingCreateDto);

}
