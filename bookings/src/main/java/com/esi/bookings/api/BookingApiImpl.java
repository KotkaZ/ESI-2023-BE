package com.esi.bookings.api;

import com.esi.bookings.BookingApi;
import com.esi.bookings.mapper.BookingsMapper;
import com.esi.bookings.models.BookingCreateDto;
import com.esi.bookings.models.BookingDto;
import com.esi.bookings.service.BookingsService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookingApiImpl implements BookingApi {

    private final BookingsService bookingsService;
    private final BookingsMapper bookingsMapper;

    @Override
    public ResponseEntity<Void> cancelBooking(Integer id) {
        log.info("Cancel booking with id {}", id);
        bookingsService.cancelBooking(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> createBooking(BookingCreateDto bookingCreateDto) {
        log.info("Create booking with dto {}", bookingCreateDto);

        val booking = bookingsMapper.bookingCreateDtoToEntity(bookingCreateDto);
        bookingsService.createBooking(booking);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<BookingDto> getBookingById(Integer id) {
        log.info("Get booking with id {}", id);

        val booking = bookingsService.getBookingById(id);
        return ResponseEntity.ok(bookingsMapper.entityToDto(booking));
    }

    @Override
    public ResponseEntity<List<BookingDto>> getBookingsByUser(Integer userId) {
        log.info("Get bookings by user id {}", userId);

        val userBookings = bookingsService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookingsMapper.entitiesToDtoList(userBookings));
    }

    @Override
    public ResponseEntity<Boolean> getRoomAvailability(Integer roomId, LocalDate startDate,
                                                       LocalDate endDate) {
        log.info("Get room availability by room id {} between {} and {}",
            roomId, startDate, endDate);

        val isAvailable = bookingsService.checkAvailability(roomId, startDate, endDate);
        return ResponseEntity.ok(isAvailable);
    }

}
