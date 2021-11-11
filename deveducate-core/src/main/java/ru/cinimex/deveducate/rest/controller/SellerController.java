package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.SellerDto;
import ru.cinimex.deveducate.service.SellerService;

import java.util.List;

@Api("API для объектов Продавцы")
@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/{id}")
    public SellerDto get(int id){

        SellerDto sellerDto = null;

        return sellerDto;
    }

    @PostMapping()
    public SellerDto save(SellerDto sellerDto){

        return sellerDto;
    }

    @GetMapping("/getAll")
    public List<SellerDto> getAll(){

        List<SellerDto> sellerDto = null;

        return  sellerDto;
    }

    @PutMapping()
    public SellerDto update(SellerDto sellerDto){

        return sellerDto;
    }

    @DeleteMapping("/{id}")
    public void remove(int id){

    }
}
