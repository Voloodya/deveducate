package ru.cinimex.deveducate.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = "vlutsenko-customers";

    private final KafkaTemplate<String, CustomerDto> kafkaTemplate;

    public void sendData(CustomerDto customerDto) {
        logger.info(String.format("#### -> Producing message -> %s", customerDto));
        this.kafkaTemplate.send(TOPIC, customerDto);
    }

}
