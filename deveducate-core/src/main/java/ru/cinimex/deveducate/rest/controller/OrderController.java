package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.service.OrderService;

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

        if (id < 0) {
            throw new ValidationException("");
        }
        return orderService.get(id);
    }

    // localhost:8080/orders
    @PostMapping()
    public OrderDto save(@RequestBody OrderDto orderDto) throws ValidationException {

        if (orderDto != null) {
            return orderService.save(orderDto);
        } else {
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
    public OrderDto update(@RequestBody OrderDto orderDto) throws ValidationException {

        if (orderDto != null && orderDto.getId() != null) {
            return orderService.update(orderDto);
        } else {
            throw new ValidationException("");
        }
    }

    // http://localhost:8080/orders/3
    @DeleteMapping("{id}")
    public void remove(@PathVariable(value = "id") int id) throws ValidationException {
        if (id < 0) {
            throw new ValidationException("Поле id обязательно для заполнения.");
        }
        orderService.remove(id);
    }

    // http://localhost:8080/orders/pageable?page=2&size=2
    @GetMapping(path = "/pageable")
    public Page<OrderDto> getPage(Pageable pageable) {
        int orderTotal = 5;
        return orderService.getSpecificPage(pageable, orderTotal);
    }

    // http://localhost:8080/orders/pageablespecific?page=1&size=2&orderTotal=5
    @GetMapping(path = "/pageablespecific")
    public Page<OrderDto> getSpecificPage(int page, int size, int orderTotal) {

        Pageable pageable = PageRequest.of(page - 1, size);
        return orderService.getSpecificPage(pageable, orderTotal);
    }

    // http://localhost:8080/orders/current
    @GetMapping(path = "/current")
    public List<OrderDto> getCurrentDate() {
        return orderService.getByCurrentDate();
    }

    // http://localhost:8080/orders/customer?id=2
    @GetMapping(path = "/customer")
    public List<OrderDto> getByCustomer(@RequestParam int id) {
        return orderService.getByCustomer(id);
    }

    // http://localhost:8080/orders/total?count=5
    @GetMapping(path = "/total")
    public List<OrderDto> getByOrderTotal(@RequestParam int count) {
        return orderService.getByOrderTotal(count);
    }

}
