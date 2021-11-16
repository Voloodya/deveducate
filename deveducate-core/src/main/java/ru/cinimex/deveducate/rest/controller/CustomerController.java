package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.CustomerService;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Api("API для объектов Покупатели")
@RestControllerAdvice
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable int id) throws ValidationException {

        if(id > 0) {
            return customerService.get(id);
        }else {
            throw new ValidationException("");
        }
    }

    @PostMapping()
    public CustomerDto save(CustomerDto customerDto) throws ValidationException {

        if(customerDto != null) {
            return customerService.save(customerDto);
        }else{
            throw new ValidationException("");
        }
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @PutMapping()
    public CustomerDto update(CustomerDto customerDto) throws ValidationException {

        if (customerDto != null && customerDto.getId() != null) {
            return customerService.update(customerDto);
        } else {
            throw new ValidationException("");
        }
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) throws ValidationException {
        if(id>0) {
            customerService.remove(id);
        }else {
            throw new ValidationException("");
        }
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                "Покупатель не найден!"
        );
        return message;
    }

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                "Некорректный запрос!"
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

}
