package com.esi.payments.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.payments.dto.PaymentTopic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducerService {

    private final KafkaTemplate<String, PaymentTopic> kafkaTemplate;

    public void publishBooking(PaymentTopic paymentTopic){
        log.info("[KAFKA] Publishing event to paymentTopic: {} ", paymentTopic.toString());
        kafkaTemplate.send("paymentTopic", paymentTopic);
    }
}