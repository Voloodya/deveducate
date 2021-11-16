package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.CustomerService;

import javax.xml.bind.ValidationException;
import java.util.List;

@Api("API для объектов Покупатели")
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable int id) throws ValidationException {

        if (id > 0) {
            return customerService.get(id);
        } else {
            throw new ValidationException("Поле id обязательно!");
        }
    }

    @PostMapping()
    public CustomerDto save(CustomerDto customerDto) throws ValidationException {

        if (customerDto != null) {
            return customerService.save(customerDto);
        } else {
            throw new ValidationException("Объект не должен быть пустым!");
        }
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @PutMapping()
    public CustomerDto update(CustomerDto customerDto) throws ValidationException {

        if (customerDto == null || customerDto.getId() == null) {
            throw new ValidationException("Поле id обязательно");
        }
        return customerService.update(customerDto);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        customerService.remove(id);
    }

}
