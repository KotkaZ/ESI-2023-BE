package com.esi.payments.services;

import com.esi.payments.mapper.PaymentsMapper;
import com.esi.payments.model.Payment;
import com.esi.payments.model.PaymentStatus;
import com.esi.payments.repository.PaymentsRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.payments.dto.PaymentEvent;
import com.esi.payments.dto.BookingEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final PaymentsRepository repo;
    private final PaymentsMapper mapper;

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void publishPayment(PaymentEvent event){
        log.info("[KAFKA] Publishing event to paymentTopic: {} ", event.toString());
        kafkaTemplate.send("paymentTopic", event);
    }

    @KafkaListener(topics = "bookingTopic", groupId = "paymentsGroup" )
    public void processBooking(BookingEvent event){
        switch(event.getStatus()) {
            case CREATED -> processCreatedBooking(event);
            default ->
                log.info("[KAFKA] Ignoring booking event: {} ", event.toString());
        }
    }

    private void processCreatedBooking(BookingEvent b) {
        log.info("[KAFKA] Processing created booking: {} ", b.toString());
        var payment = Payment.builder()
                .bookingId(b.getId())
                .userId(b.getUserId())
                .price(b.getPrice())
                .totalAmount(b.getPrice().multiply(BigDecimal.valueOf(1.2)))
                .status(PaymentStatus.PENDING)
                .changedAt(OffsetDateTime.now())
                .build();

        // paying immediately
        payment.setStatus(PaymentStatus.SUCCEEDED);
        payment.setChangedAt(OffsetDateTime.now());
        //

        repo.saveAndFlush(payment);

        var eventMsg = mapper.mapToEvent(payment);

        publishPayment(eventMsg);
    }
}