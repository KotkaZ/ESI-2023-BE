package com.esi.bookings.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic BookingTopicCreation(){
        return TopicBuilder.name("bookingTopic")
        .build();
    }

    @Bean
    public NewTopic PaymentTopicCreation(){
        return TopicBuilder.name("paymentTopic")
                .build();
    }

    @Bean
    public NewTopic CheckingTopicCreation(){
        return TopicBuilder.name("checkingTopic")
                .build();
    }
}