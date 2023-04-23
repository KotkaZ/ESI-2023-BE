package com.esi.checking.service;

import com.esi.checking.mapper.CheckingMapper;
import com.esi.checking.model.Checking;
import com.esi.checking.repository.CheckingRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.checking.dto.CheckingEvent;
import com.esi.checking.dto.BookingEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final CheckingMapper mapper;
    private static final Random random = new java.util.Random();

    private final CheckingRepository repo;
    private final KafkaTemplate<String, CheckingEvent> kafkaTemplate;

    public void publishChecking(CheckingEvent event){
        log.info("[KAFKA] Publishing event to checkingTopic: {} ", event.toString());
        kafkaTemplate.send("checkingTopic", event);
    }

    @KafkaListener(topics = "bookingTopic", groupId = "checkingGroup")
    public void processBooking(BookingEvent event){
        log.info("[KAFKA] Log message - received from booking topic: {} ", event.toString());
        switch (event.getStatus()) {
            case BOOKED -> processSuccessBooking(event);
            default -> log.info("[KAFKA] Log message - ignoring booking with status {} : {} ",
                    event.getStatus(), event.toString());
        }
    }

    private void processSuccessBooking(BookingEvent event){
        log.info("[KAFKA] Log message - received booked booking: {} ", event.toString());
        String code = String.format("%04d", random.nextInt(9000) + 1000); // from 1000 to 9999
        var checking = Checking.builder()
                .bookingId(event.getId())
                .roomId(event.getRoomId())
                .code(code)
                .build();

        repo.saveAndFlush(checking);

        var eventMsg = mapper.mapToEvent(checking);

        publishChecking(mapper.mapToEvent(checking));
    }
}