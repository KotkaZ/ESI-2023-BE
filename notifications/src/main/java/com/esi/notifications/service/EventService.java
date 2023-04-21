package com.esi.notifications.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.notifications.dto.BookingEvent;
import com.esi.notifications.dto.PaymentEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    @KafkaListener(topics = "bookingTopic", groupId = "notificationGroup")
    public void processChecking(BookingEvent event){
        log.info("[KAFKA] Log message - recieved from booking topic: {} ", event.toString());
    }

    @KafkaListener(topics = "paymentTopic", groupId = "notificationGroup")
    public void processChecking(PaymentEvent event){
        log.info("[KAFKA] Log message - recieved from payment topic: {} ", event.toString());
    }
}