package com.esi.bookings.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.bookings.dto.BookingCreated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, BookingCreated> kafkaTemplate;
    
    public void publishBooking(BookingCreated booking){
        log.info("[KAFKA] Publishing booking to bookingCreatedTopic: {} ", booking.toString());
        kafkaTemplate.send("bookingCreatedTopic", booking);
    }
}