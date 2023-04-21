package com.esi.checking.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.checking.dto.CheckingTopic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducerService {

    private final KafkaTemplate<String, CheckingTopic> kafkaTemplate;

    public void publishBooking(CheckingTopic checkingTopic){
        log.info("[KAFKA] Publishing event to checkingTopic: {} ", checkingTopic.toString());
        kafkaTemplate.send("checkingTopic", checkingTopic);
    }
}