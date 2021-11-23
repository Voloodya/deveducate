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
    public CustomerDto get(@PathVariable(value = "id") int id) throws ValidationException {

        if (id < 0) {
            throw new ValidationException("Поле id некорректно!");
        }
        return customerService.get(id);
    }

    @PostMapping()
    public CustomerDto save(@RequestBody CustomerDto customerDto) throws ValidationException {

        if (customerDto == null) {
            throw new ValidationException("Объект не должен быть пустым!");
        }
        return customerService.save(customerDto);
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @PutMapping()
    public CustomerDto update(@RequestBody CustomerDto customerDto) throws ValidationException {

        if (customerDto == null || customerDto.getId() == null) {
            throw new ValidationException("Поле id обязательно");
        }
        return customerService.update(customerDto);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable(value = "id") int id) throws ValidationException {
        if (id < 0) {
            throw new ValidationException("");
        }
        customerService.remove(id);
    }

}
