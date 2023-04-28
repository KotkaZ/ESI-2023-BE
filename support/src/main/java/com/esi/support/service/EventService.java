package com.esi.support.service;

import com.esi.support.model.Support;
import com.esi.support.repository.SupportRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.support.dto.CheckingEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final SupportRepository repository;

    @KafkaListener(topics = "checkingTopic", groupId = "supportGroup")
    public void processChecking(CheckingEvent event){
        log.info("[KAFKA] Log message - recieved from checking topic: {} ", event.toString());

        if (event.getCheckOutAt() != null) {
            var cleaningItem = Support
                    .builder()
                    .bookingId(event.getBookingId())
                    .roomId(event.getRoomId())
                    .requestedAt(OffsetDateTime.now())
                    .build();

            repository.saveAndFlush(cleaningItem);
        }
    }
}