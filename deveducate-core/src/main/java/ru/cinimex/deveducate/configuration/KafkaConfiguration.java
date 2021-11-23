package ru.cinimex.deveducate.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@RequiredArgsConstructor
@Configuration
public class KafkaConfiguration {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfiguration.class);

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
        logger.info("Start configuration Topic Kafka");
        NewTopic newTopic = TopicBuilder.name("vlutsenko-customers")
                .partitions(6)
                .replicas(3)
                .build();
        logger.info("Finished configuration Topic Kafka: name - ", newTopic.name() + "; configs - " + newTopic.configs());

        return newTopic;
    }

//    @Bean
//    public ConsumerFactory<Integer, String> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerProps());
//    }
//
//    private Map<String, Object> consumerProps() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "devedu-01.vm.cmx.ru:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_vlutsenko");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        // ...
//        return props;
//    }
}
