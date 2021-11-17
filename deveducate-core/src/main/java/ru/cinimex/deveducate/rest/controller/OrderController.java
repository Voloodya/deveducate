package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.rest.exception.RestExceptionHandler;
import ru.cinimex.deveducate.service.OrderService;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Api("API для объектов Ордер")
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("{id}")
    public OrderDto get(@PathVariable int id) throws ValidationException {

        if(id > 0) {
            return orderService.get(id);
        }else {
            throw new ValidationException("");
        }
    }

    @PostMapping()
    public OrderDto save(OrderDto orderDto) throws ValidationException {

        if(orderDto != null) {
            return orderService.save(orderDto);
        }else{
            throw new ValidationException("");
        }
    }

    @GetMapping("/getAll")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PutMapping()
    public OrderDto update(OrderDto orderDto) throws ValidationException {

        if(orderDto != null && orderDto.getOrderId() != null) {
            return orderService.update(orderDto);
        }else{
            throw new ValidationException("");
        }
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable int id) throws ValidationException {
        if(id>0) {
            orderService.remove(id);
        }else {
            throw new ValidationException("");
        }
    }

    @GetMapping(path = "/pageable")
    public Page<OrderDto> getPage(Integer page, Integer size){

        Pageable pageable = PageRequest.of(page-1, size);
        return orderService.getPage(pageable);
    }
}
