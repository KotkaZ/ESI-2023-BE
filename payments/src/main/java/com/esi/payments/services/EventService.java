package com.esi.payments.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.payments.dto.PaymentEvent;
import com.esi.payments.dto.BookingEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void publishPayment(PaymentEvent event){
        log.info("[KAFKA] Publishing event to paymentTopic: {} ", event.toString());
        kafkaTemplate.send("paymentTopic", event);
    }

    @KafkaListener(topics = "bookingTopic", groupId = "paymentsGroup" )
    public void processBooking(BookingEvent event){
        log.info("[KAFKA] Log message - recieved from booking topic: {} ", event.toString());
    }
}