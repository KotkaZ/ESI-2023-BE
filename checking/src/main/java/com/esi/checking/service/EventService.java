package com.esi.checking.service;

import static com.esi.constants.GroupIds.CHECKING_GROUP;
import static com.esi.constants.Topics.BOOKING_TOPIC;
import static com.esi.constants.Topics.CHECKING_TOPIC;

import com.esi.events.BookingEvent;
import com.esi.events.CheckingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, CheckingEvent> kafkaTemplate;

    public void publishChecking(CheckingEvent event) {
        log.info("[KAFKA] Publishing event to checkingTopic: {} ", event.toString());
        kafkaTemplate.send(CHECKING_TOPIC, event);
    }

    @KafkaListener(topics = BOOKING_TOPIC, groupId = CHECKING_GROUP)
    public void processBooking(BookingEvent event) {
        log.info("[KAFKA] Log message - received from booking topic: {} ", event.toString());
    }
}