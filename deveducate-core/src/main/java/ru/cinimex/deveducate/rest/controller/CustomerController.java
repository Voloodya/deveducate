package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
            throw new ValidationException("Объект и его id должны быть обязательно заполнены!");
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
