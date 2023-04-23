package com.esi.notifications.service;

import com.esi.notifications.dto.BookingStatus;
import com.esi.notifications.model.Notification;
import com.esi.notifications.model.NotificationStatus;
import com.esi.notifications.model.NotificationType;
import com.esi.notifications.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.notifications.dto.BookingEvent;
import com.esi.notifications.dto.PaymentEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final NotificationRepository repository;

    @KafkaListener(topics = "bookingTopic", groupId = "notificationGroup")
    public void processBooking(BookingEvent event){
        log.info("[KAFKA] Log message - received from booking topic: {} ", event.toString());
        switch (event.getStatus()) {
            case CREATED, BOOKED, CANCELLED ->
                    processBookingConfirm(event, NotificationStatus.CREATED);
            default -> log.info("[KAFKA] Log message - booking status unknown: {} ", event.toString());
        }
    }

    private void processBookingConfirm(BookingEvent event, NotificationStatus status) {
        log.info("[KAFKA] Log message - booking processed: {} ", event.toString());
        var notification = Notification.builder()
                .bookingId(event.getId())
                .type(NotificationType.CONFIRMATION)
                .status(status)
                .changedAt(OffsetDateTime.now())
                .build();
        if(event.getStatus().equals(BookingStatus.BOOKED)) {
            notification.setPaymentId(event.getPaymentId());
        }

        repository.saveAndFlush(notification);
    }

    @KafkaListener(topics = "paymentTopic", groupId = "notificationGroup")
    public void processPayment(PaymentEvent event){
        processBookingInvoice(event, NotificationStatus.CREATED);
    }

    private void processBookingInvoice(PaymentEvent event,NotificationStatus status) {
        log.info("[KAFKA] Log message - recieved from payment topic: {} ", event.toString());
        var notification = Notification.builder()
                .bookingId(event.getBookingId())
                .paymentId(event.getId())
                .type(NotificationType.INVOICE)
                .status(status)
                .changedAt(OffsetDateTime.now())
                .build();
        repository.saveAndFlush(notification);
    }
}