package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                "Заказ не найден!"
        );
        return message;
    }

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                "Ошибка валидации!"
        );
        return message;
    }

    @ExceptionHandler(value = {ServerErrorException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage entityNotFoundException(ServerErrorException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                "Ошибка сервера!"
        );
        return message;
    }

    @GetMapping(path = "/pageable")
    public Slice<OrderDto> loadOrdersPage(@PathVariable Pageable pageable){

        return orderService.getAllSlicePage(pageable);

    }
}
