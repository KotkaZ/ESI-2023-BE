package com.esi.support.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.support.dto.CheckingEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    @KafkaListener(topics = "checkingTopic", groupId = "supportGroup")
    public void processChecking(CheckingEvent event){
        log.info("[KAFKA] Log message - recieved from checking topic: {} ", event.toString());
    }
}