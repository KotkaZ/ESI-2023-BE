package com.esi.bookings.service;

import com.esi.bookings.dto.CheckingEvent;
import com.esi.bookings.mapper.BookingsMapper;
import com.esi.bookings.model.BookingStatus;
import com.esi.bookings.repository.BookingsRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.bookings.dto.BookingEvent;
import com.esi.bookings.dto.PaymentEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final BookingsMapper mapper;
    private final BookingsRepository repo;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;
    
    public void publishBooking(BookingEvent event){
        log.info("[KAFKA] Publishing booking to bookingCreatedTopic: {} ", event.toString());
        kafkaTemplate.send("bookingTopic", event);
    }

    @KafkaListener(topics = "paymentTopic", groupId = "bookingGroup" )
    public void processPayment(PaymentEvent event){
        log.info("[KAFKA] Log message - received from payment topic: {} ", event.toString());
        switch (event.getStatus()) {
            case SUCCEEDED -> processSuccessPayment(event);
            default -> log.info("[KAFKA] Log message - recieved from payment topic: {} ", event.toString());
        }
    }

    private void processSuccessPayment(PaymentEvent event){
        log.info("[KAFKA] Log message - recieved from payment topic: {} ", event.toString());
        var booking = repo.findById(event.getBookingId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Booking not found"));

        booking.setStatus(BookingStatus.BOOKED);
        booking.setPaymentId(event.getId());
        booking.setModifiedAt(OffsetDateTime.now());

        repo.saveAndFlush(booking);

        var eventMsg = mapper.mapToEvent(booking);
        publishBooking(eventMsg);
    }

    @KafkaListener(topics = "checkingTopic", groupId = "bookingGroup" )
    public void processChecking(CheckingEvent event){
        log.info("[KAFKA] Log message - received from checking topic: {} ", event.toString());
        var booking = repo.findById(event.getBookingId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Booking not found"));

        booking.setCheckinId(event.getId());
        booking.setModifiedAt(OffsetDateTime.now());

        repo.saveAndFlush(booking);
    }
}