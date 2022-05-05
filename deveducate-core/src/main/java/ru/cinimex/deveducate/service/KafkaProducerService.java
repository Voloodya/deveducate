package ru.cinimex.deveducate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private static final String TOPIC = "vlutsenko-customers";

    private final KafkaTemplate<String, CustomerDto> kafkaTemplate;

    public void sendData(CustomerDto customerDto) {
        log.info("Producing message {}", customerDto);
        this.kafkaTemplate.send(TOPIC, customerDto);
    }

}
