package com.esi.bookings.configuration;

import static com.esi.constants.Topics.BOOKING_TOPIC;
import static com.esi.constants.Topics.CHECKING_TOPIC;
import static com.esi.constants.Topics.PAYMENT_TOPIC;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic BookingTopicCreation() {
        return TopicBuilder.name(BOOKING_TOPIC).build();
    }

    @Bean
    public NewTopic PaymentTopicCreation() {
        return TopicBuilder.name(PAYMENT_TOPIC).build();
    }

    @Bean
    public NewTopic CheckingTopicCreation() {
        return TopicBuilder.name(CHECKING_TOPIC).build();
    }
}