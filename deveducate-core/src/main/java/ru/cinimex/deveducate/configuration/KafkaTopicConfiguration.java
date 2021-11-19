package ru.cinimex.deveducate.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Slf4j
@Configuration
public class KafkaTopicConfiguration {

    private final Logger logger = LoggerFactory.getLogger(KafkaTopicConfiguration.class);

    @Bean
    public NewTopic topicSettings() {
        logger.info("Start configuration Topic Kafka");
        NewTopic newTopic = TopicBuilder.name("vlutsenko-customers")
                .partitions(6)
                .replicas(3)
                .build();
        logger.info("Finished configuration Topic Kafka: name - " + newTopic.name()+"; configs - " + newTopic.configs());

        return newTopic;
    }
}
