package com.esi.checking.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.checking.dto.CheckingEvent;
import com.esi.checking.dto.BookingEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, CheckingEvent> kafkaTemplate;

    public void publishChecking(CheckingEvent event){
        log.info("[KAFKA] Publishing event to checkingTopic: {} ", event.toString());
        kafkaTemplate.send("checkingTopic", event);
    }

    @KafkaListener(topics = "bookingTopic", groupId = "bookingEventGroup")
    public void processBooking(BookingEvent event){
        log.info("Log message - recieved from booking topic: {} ", event.toString());
    }
}