package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.CustomerService;

import java.util.List;

@Api("API для объектов Покупатели")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto get(int id){

        CustomerDto customerDto = null;

        return customerDto;
    }

    @PostMapping()
    public CustomerDto save(CustomerDto customerDto){

        return customerDto;
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getAll(){

        List<CustomerDto> customerDto = null;

        return  customerDto;
    }

    @PutMapping()
    public CustomerDto update(CustomerDto customerDto){

        return customerDto;
    }

    @DeleteMapping("/{id}")
    public void remove(int id){

    }

}