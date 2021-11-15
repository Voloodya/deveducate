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
@RestControllerAdvice
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable int id){

        return new ResponseEntity<>(customerService.get(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> save(CustomerDto customerDto){

        if(customerDto != null) {
             customerDto = customerService.save(customerDto);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerDto, HttpStatus.OK) ;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getAll(){

        return new ResponseEntity<>(customerService.getAll(),HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<CustomerDto> update(CustomerDto customerDto){

        if(customerDto != null) {
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
