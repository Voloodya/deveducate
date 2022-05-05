package ru.cinimex.deveducate.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.KafkaListenerService;
import ru.cinimex.deveducate.service.KafkaProducerService;

import java.util.Optional;

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
    public ResponseEntity<CustomerDto> get(@PathVariable(value = "id") int id) {

        Optional<CustomerDto> customer = kafkaListener.getCustomer(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
