package com.esi.support.service;

import static com.esi.constants.GroupIds.SUPPORT_GROUP;
import static com.esi.constants.Topics.CHECKING_TOPIC;

import com.esi.events.CheckingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    @KafkaListener(topics = CHECKING_TOPIC, groupId = SUPPORT_GROUP)
    public void processChecking(CheckingEvent event) {
        log.info("[KAFKA] Log message - received from checking topic: {} ", event.toString());
    }
}