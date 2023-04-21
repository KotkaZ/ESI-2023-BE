package com.esi.bookings.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.bookings.dto.BookingEvent;
import com.esi.bookings.dto.PaymentEvent;
import com.esi.bookings.dto.CheckingEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;
    
    public void publishBooking(BookingEvent event){
        log.info("[KAFKA] Publishing booking to bookingCreatedTopic: {} ", event.toString());
        kafkaTemplate.send("bookingTopic", event);
    }

    @KafkaListener(topics = "paymentTopic", groupId = "bookingGroup" )
    public void processPayment(PaymentEvent event){
        log.info("[KAFKA] Log message - recieved from payment topic: {} ", event.toString());
    }

    @KafkaListener(topics = "checkingTopic", groupId = "bookingGroup" )
    public void processChecking(CheckingEvent event){
        log.info("[KAFKA] Log message - recieved from checking topic: {} ", event.toString());
    }
}