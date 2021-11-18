package ru.cinimex.deveducate.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic topicSettings() {
        return TopicBuilder.name("vlutsenko-customers")
                .partitions(6)
                .replicas(3)
                .build();
    }
}
