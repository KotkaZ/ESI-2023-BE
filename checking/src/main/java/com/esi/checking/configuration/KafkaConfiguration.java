package com.esi.checking.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic CheckingTopicCreation(){
        return TopicBuilder.name("checkingTopic")
                .build();
    }

    @Bean
    public NewTopic BookingTopicCreation(){
        return TopicBuilder.name("bookingTopic")
                .build();
    }
}