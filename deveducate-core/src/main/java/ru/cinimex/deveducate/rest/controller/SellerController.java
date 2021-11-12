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
@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/{id}")
    public SellerDto get(@PathVariable int id){

        SellerDto sellerDto = sellerService.get(id);

        return sellerDto;
    }

    @PostMapping()
    public SellerDto save(SellerDto sellerDto){
        if(sellerDto != null) {
            sellerDto = sellerService.save(sellerDto);
        }else{
            sellerDto = null;
        }
        return sellerDto;
    }

    @GetMapping("/getAll")
    public List<SellerDto> getAll(){

        List<SellerDto> sellerDtoList = sellerService.getAll();

        return  sellerDtoList;
    }

    @PutMapping()
    public SellerDto update(SellerDto sellerDto){

        return sellerDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id){
        try {
            sellerService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
