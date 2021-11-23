package ru.cinimex.deveducate.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.KafkaListenerService;
import ru.cinimex.deveducate.service.KafkaProducerService;

@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService kafkaService;
    private final KafkaListenerService kafkaListener;

    @PostMapping
    public void add(@RequestBody CustomerDto customerDto) {

        kafkaService.sendData(customerDto);
    }

    @GetMapping("{id}")
    public CustomerDto get(@PathVariable(value = "id") int id) {

        return kafkaListener.getCustomer(id);
    }
}
