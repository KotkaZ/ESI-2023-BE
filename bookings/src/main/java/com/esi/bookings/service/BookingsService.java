package com.esi.bookings.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.esi.bookings.model.Booking;
import com.esi.bookings.model.BookingStatus;
import com.esi.bookings.mapper.BookingCreatedMapper;
import com.esi.bookings.repository.BookingsRepository;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookingsService {

    private final BookingsRepository bookingsRepository;
    private final BookingCreatedMapper bookingCreatedMapper;
    @Autowired
    private final ProducerService producerService;

    public void createBooking(Booking booking) {
        // TODO: 21/04/2023 What kind of validation? Manual? Bean?

        bookingsRepository.saveAndFlush(booking);

        var topic = bookingCreatedMapper.mapToTopic(booking);

        producerService.publishBooking(topic);
    }

    public Booking getBookingById(Integer id) {
        return bookingsRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Booking not found"));
    }

    public Booking getBookingByRoomId(Integer roomId) {
        return bookingsRepository.findByRoomId(roomId)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Booking not found"));
    }

    public List<Booking> getBookingsByUserId(Integer userId) {
        return bookingsRepository.findAllByUserId(userId);
    }

    public void cancelBooking(Integer id) {
        val booking = getBookingById(id);

        booking.setStatus(BookingStatus.CANCELLED);
        booking.setModifiedAt(OffsetDateTime.now());

        bookingsRepository.saveAndFlush(booking);
    }
}
