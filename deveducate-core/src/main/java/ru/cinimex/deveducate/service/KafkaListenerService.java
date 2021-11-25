package ru.cinimex.deveducate.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class KafkaListenerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);
    private final ConcurrentHashMap<Integer, CustomerDto> concurrentHashMap = new ConcurrentHashMap<>();


    @KafkaListener(topics = "vlutsenko-customers", groupId = "group_vlutsenko")
    public void consume(CustomerDto consumerDto) {

        concurrentHashMap.put(consumerDto.getId(), consumerDto);

    }

    public CustomerDto getCustomer(int id) {

        CustomerDto customerDto = null;

        try {
            customerDto = concurrentHashMap.get(id);
            concurrentHashMap.remove(id);
        } catch (Exception ex) {
            logger.error("Error in KafkaListenerService: ", ex);
            throw new NullPointerException();
        }
        return customerDto;
    }
}
