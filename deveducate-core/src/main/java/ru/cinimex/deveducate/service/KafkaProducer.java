package ru.cinimex.deveducate.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

@RequiredArgsConstructor
@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String TOPIC = "vlutsenko-customers";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendData(CustomerDto customerDto) {
        logger.info(String.format("#### -> Producing message -> %s", customerDto));
        this.kafkaTemplate.send(TOPIC, String.valueOf(customerDto));
    }

}
