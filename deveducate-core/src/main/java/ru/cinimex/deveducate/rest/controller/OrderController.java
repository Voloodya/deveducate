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
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}")
    public OrderDto get(@PathVariable int id){

        OrderDto orderDto = orderService.get(id);

        return orderDto;
    }

    @PostMapping()
    public OrderDto save(OrderDto orderDto){

        return orderDto;
    }

    @GetMapping("/getAll")
    public List<OrderDto> getAll(){
        List<OrderDto> orderDtoList = orderService.getAll();

        return  orderDtoList;
    }

    @PutMapping()
    public OrderDto update(OrderDto orderDto){

        return orderDto;
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable int id){
        try {
            orderService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
}
