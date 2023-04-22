package com.esi.payments.service;

import static com.esi.constants.GroupIds.PAYMENT_GROUP;
import static com.esi.constants.Topics.BOOKING_TOPIC;
import static com.esi.constants.Topics.PAYMENT_TOPIC;

import com.esi.events.BookingEvent;
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

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void publishPayment(PaymentEvent event) {
        log.info("[KAFKA] Publishing event to paymentTopic: {} ", event.toString());
        kafkaTemplate.send(PAYMENT_TOPIC, event);
    }

    @KafkaListener(topics = BOOKING_TOPIC, groupId = PAYMENT_GROUP)
    public void processBooking(BookingEvent event) {
        log.info("[KAFKA] Log message - received from booking topic: {} ", event.toString());
    }
}