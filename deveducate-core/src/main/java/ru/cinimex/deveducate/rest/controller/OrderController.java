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

    // http://localhost:8080/orders/1
    @GetMapping("{id}")
    public OrderDto get(@PathVariable(value = "id") int id) throws ValidationException {

        if(id > 0) {
            return orderService.get(id);
        }else {
            throw new ValidationException("");
        }
    }

    // localhost:8080/orders
    @PostMapping()
    public OrderDto save(OrderDto orderDto) throws ValidationException {

        if(orderDto != null) {
            return orderService.save(orderDto);
        }else{
            throw new ValidationException("");
        }
    }

    // http://localhost:8080/orders/getAll
    @GetMapping("/getAll")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    // localhost:8080/orders
    @PutMapping()
    public OrderDto update(OrderDto orderDto) throws ValidationException {

        if(orderDto != null && orderDto.getOrderId() != null) {
            return orderService.update(orderDto);
        }else{
            throw new ValidationException("");
        }
    }

    // http://localhost:8080/orders/3
    @DeleteMapping("{id}")
    public void remove(@PathVariable(value = "id") int id) throws ValidationException {
        if(id>0) {
            orderService.remove(id);
        }else {
            throw new ValidationException("");
        }
    }

    // http://localhost:8080/orders/pageable?page=2&size=2
    @GetMapping(path = "/pageable")
    public Page<OrderDto> getPage(int page, int size){

        Pageable pageable = PageRequest.of(page-1, size);
        return orderService.getPage(pageable);
    }

    // http://localhost:8080/orders/pageablespecific?page=1&size=2&orderTotal=5
    @GetMapping(path = "/pageablespecific")
    public Page<OrderDto> getSpecificPage(int page, int size, int orderTotal){

        Pageable pageable = PageRequest.of(page-1, size);
        return orderService.getSpecificPage(pageable, orderTotal);
    }

    @GetMapping(path = "/current")
    public List<OrderDto> getCurrentDate(){
        return orderService.getCurrentDate();
    }

    @GetMapping(path = "/customer")
    public List<OrderDto> getByCustomer(@RequestParam int id){
        return orderService.getByCustomer(id);
    }

    // http://localhost:8080/orders/total?count=5
    @GetMapping(path = "/total")
    public List<OrderDto> getByOrderTotal(@RequestParam int count){
        return orderService.getByOrderTotal(count);
    }

}
