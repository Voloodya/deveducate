package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.SellerDto;
import ru.cinimex.deveducate.service.SellerService;

import java.util.List;

@Api("API для объектов Продавцы")
@RestControllerAdvice
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> get(@PathVariable int id) {

        return new ResponseEntity<>(sellerService.get(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SellerDto> save(SellerDto sellerDto) {
        if (sellerDto != null) {
            return new ResponseEntity<>(sellerService.save(sellerDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SellerDto>> getAll() {

        return new ResponseEntity<>(sellerService.getAll(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<SellerDto> update(SellerDto sellerDto) {

        return new ResponseEntity<>(sellerService.update(sellerDto), HttpStatus.OK);
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
