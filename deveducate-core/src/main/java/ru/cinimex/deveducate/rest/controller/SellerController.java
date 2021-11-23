package ru.cinimex.deveducate.rest.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cinimex.deveducate.rest.dto.SellerDto;
import ru.cinimex.deveducate.service.SellerService;

import javax.xml.bind.ValidationException;
import java.util.List;

@Api("API для объектов Продавцы")
@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/{id}")
    public SellerDto get(@PathVariable(value = "id") int id) throws ValidationException {

        if (id < 0) {
            throw new ValidationException("");
        }
        return sellerService.get(id);
    }

    @PostMapping()
    public SellerDto save(@RequestBody SellerDto sellerDto) throws ValidationException {
        if (sellerDto == null) {
            throw new ValidationException("");
        }
        return sellerService.save(sellerDto);
    }

    @GetMapping("/getAll")
    public List<SellerDto> getAll() {

        return sellerService.getAll();
    }

    @PutMapping()
    public SellerDto update(@RequestBody SellerDto sellerDto) throws ValidationException {

        if (sellerDto != null && sellerDto.getId() != null) {
            return sellerService.update(sellerDto);
        } else {
            throw new ValidationException("");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable(value = "id") int id) {
        try {
            sellerService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
