package ru.cinimex.deveducate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

@Service
public class KafkaListener_ {

    private final Logger logger = LoggerFactory.getLogger(KafkaListener_.class);

    @KafkaListener(topics = "vlutsenko-customers", groupId = "group_id")
    public CustomerDto consume(CustomerDto consumerDto){
        logger.info(String.format("#### -> Consumed message -> %s", consumerDto));
        return null;
    }

    public CustomerDto getCustomer(int id){

        return null;
    }
}
