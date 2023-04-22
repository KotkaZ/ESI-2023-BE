package com.esi.notifications.service;

import static com.esi.constants.GroupIds.NOTIFICATION_GROUP;
import static com.esi.constants.Topics.BOOKING_TOPIC;
import static com.esi.constants.Topics.PAYMENT_TOPIC;

import com.esi.events.BookingEvent;
import com.esi.events.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    @KafkaListener(topics = BOOKING_TOPIC, groupId = NOTIFICATION_GROUP)
    public void processBooking(BookingEvent event) {
        log.info("[KAFKA] Log message - received from booking topic: {} ", event.toString());
    }

    @KafkaListener(topics = PAYMENT_TOPIC, groupId = NOTIFICATION_GROUP)
    public void processPayment(PaymentEvent event) {
        log.info("[KAFKA] Log message - received from payment topic: {} ", event.toString());
    }
}