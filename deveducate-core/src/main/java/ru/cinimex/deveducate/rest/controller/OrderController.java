package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.service.OrderService;

import java.util.List;

@Api("API для объектов Ордер")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto get(int id){

        OrderDto orderDto = null;

        return orderDto;
    }

    @PostMapping()
    public OrderDto save(OrderDto orderDto){

        return orderDto;
    }

    @GetMapping("/getAll")
    public List<OrderDto> getAll(){
        List<OrderDto> orderDtoList = null;

        return  orderDtoList;
    }

    @PutMapping()
    public OrderDto update(OrderDto orderDto){

        return orderDto;
    }

    @DeleteMapping("/{id}")
    public void remove(int id){

    }
}
