package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.service.OrderService;

import java.util.List;

@Api("API для объектов Ордер")
@RestControllerAdvice
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> get(@PathVariable int id) {

        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDto> save(OrderDto orderDto) {

        return new ResponseEntity<>(orderService.save(orderDto), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAll() {

        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<OrderDto> update(OrderDto orderDto) {

        return new ResponseEntity<>(orderService.update(orderDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable int id) {
        try {
            orderService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
}
