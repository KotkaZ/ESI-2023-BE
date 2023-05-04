package com.esi.bookings.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.esi.bookings.mapper.BookingsMapper;
import com.esi.bookings.model.Booking;
import com.esi.bookings.model.BookingStatus;
import com.esi.bookings.repository.BookingsRepository;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookingsService {

    public static final String ROOMS_SERVICE_BASE_PATH = "http://rooms-service/api/rooms";
    private final BookingsRepository bookingsRepository;
    private final BookingsMapper bookingsMapper;

    private final WebClient.Builder webClientBuilder;

    private final EventService eventService;

    public void createBooking(Booking booking) {
        // TODO: 21/04/2023 What kind of validation? Manual? Bean?

        checkIfRoomExists(booking.getRoomId());

        booking.setModifiedAt(OffsetDateTime.now());

        bookingsRepository.saveAndFlush(booking);

        var topic = bookingsMapper.mapToEvent(booking);

        eventService.publishBooking(topic);
    }

    public Booking getBookingById(Integer id) {
        return bookingsRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Booking not found"));
    }

    public boolean checkAvailability(Integer roomId, LocalDate startDate, LocalDate endDate) {
        checkIfRoomExists(roomId);
        return !bookingsRepository
            .existsBookingInSpecificTimeRange(roomId, endDate, startDate)
            .orElse(false);
    }

    public List<Booking> getBookingsByUserId(Integer userId) {
        return bookingsRepository.findAllByUserId(userId);
    }

    public void cancelBooking(Integer id) {
        val booking = getBookingById(id);

        booking.setStatus(BookingStatus.CANCELLED);
        booking.setModifiedAt(OffsetDateTime.now());

        bookingsRepository.saveAndFlush(booking);

        eventService.publishBooking(bookingsMapper.mapToEvent(booking));
    }

    // Throws an exception if the room does not exist
    private void checkIfRoomExists(Integer roomId) {
        webClientBuilder
            .build()
            .get()
            .uri(ROOMS_SERVICE_BASE_PATH + "/{id}", roomId)
            .retrieve()
            .onStatus(NOT_FOUND::equals,
                response -> Mono.error(new ResponseStatusException(NOT_FOUND, "Room not found")))
            .toBodilessEntity()
            .block();
    }
}
