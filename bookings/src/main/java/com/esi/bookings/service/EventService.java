package com.esi.bookings.service;

import static com.esi.constants.GroupIds.BOOKING_GROUP;
import static com.esi.constants.Topics.BOOKING_TOPIC;
import static com.esi.constants.Topics.CHECKING_TOPIC;
import static com.esi.constants.Topics.PAYMENT_TOPIC;

import com.esi.events.BookingEvent;
import com.esi.events.CheckingEvent;
import com.esi.events.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public void publishBooking(BookingEvent event) {
        log.info("[KAFKA] Publishing booking to bookingCreatedTopic: {} ", event.toString());
        kafkaTemplate.send(BOOKING_TOPIC, event);
    }

    @KafkaListener(topics = PAYMENT_TOPIC, groupId = BOOKING_GROUP)
    public void processPayment(PaymentEvent event) {
        log.info("[KAFKA] Log message - received from payment topic: {} ", event.toString());
    }

    @KafkaListener(topics = CHECKING_TOPIC, groupId = BOOKING_GROUP)
    public void processChecking(CheckingEvent event) {
        log.info("[KAFKA] Log message - received from checking topic: {} ", event.toString());
    }
}