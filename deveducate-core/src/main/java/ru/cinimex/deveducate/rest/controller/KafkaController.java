package ru.cinimex.deveducate.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.KafkaListener_;
import ru.cinimex.deveducate.service.KafkaProducer;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducer kafkaService;
    private final KafkaListener_ kafkaListener;

    @PostMapping
    public void add(@RequestBody CustomerDto customerDto) {

        kafkaService.sendData(customerDto);
    }

    @GetMapping("{id}")
    public void get(@PathVariable(value = "id") int id) {

        kafkaListener.getCustomer(id);
    }
}
