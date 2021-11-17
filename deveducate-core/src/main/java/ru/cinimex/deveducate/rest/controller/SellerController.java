package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import ru.cinimex.deveducate.rest.dto.SellerDto;
import ru.cinimex.deveducate.rest.exception.RestExceptionHandler;
import ru.cinimex.deveducate.service.SellerService;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Api("API для объектов Продавцы")
@RestControllerAdvice
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/{id}")
    public SellerDto get(@PathVariable int id) throws ValidationException {

        if(id > 0) {
            return sellerService.get(id);
        }else {
            throw new ValidationException("");
        }
    }

    @PostMapping()
    public SellerDto save(SellerDto sellerDto) throws ValidationException {
        if(sellerDto != null) {
            return sellerService.save(sellerDto);
        }else{
            throw new ValidationException("");
        }
    }

    @GetMapping("/getAll")
    public List<SellerDto> getAll() {

        return sellerService.getAll();
    }

    @PutMapping()
    public SellerDto update(SellerDto sellerDto) throws ValidationException {

        if(sellerDto != null && sellerDto.getId() != null) {
            return sellerService.update(sellerDto);
        }else{
            throw new ValidationException("");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id) {
        try {
            sellerService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
