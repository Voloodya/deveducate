package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CustomerDto get(@PathVariable int id){

        CustomerDto customerDto = customerService.get(id);
        return customerDto;
    }

    @PostMapping()
    public CustomerDto save(CustomerDto customerDto){

        if(customerDto != null) {
             customerDto = customerService.save(customerDto);
        }else{
            customerDto = null;
        }
        return customerDto;
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getAll(){

        List<CustomerDto> customerDto = customerService.getAll();

        return  customerDto;
    }

    @PutMapping()
    public CustomerDto update(CustomerDto customerDto){

        CustomerDto customerDtoUpdate;
        if(customerDto != null) {
            customerDtoUpdate = customerService.update(customerDto);
        }
        else{
            customerDtoUpdate = null;
        }
        return customerDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id){

        try {
            customerService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
