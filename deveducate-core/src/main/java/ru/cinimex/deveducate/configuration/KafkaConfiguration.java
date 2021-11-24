package ru.cinimex.deveducate.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@RequiredArgsConstructor
@Configuration
public class KafkaConfiguration {


    private final String nameTopic = "vlutsenko-customers";
    private final int partitions = 6;
    private final int replicas = 3;
    @Value(value = "${spring.kafka.bootstrap-servers: localhost:9092}") // localhost:9092 - default value
    private String bootstrapServers;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        // Depending on you Kafka Cluster setup you need to configure additional properties!
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicSettings() {
        NewTopic newTopic = TopicBuilder.name(nameTopic)
                .partitions(partitions)
                .replicas(replicas)
                .build();
        return newTopic;
    }

}
