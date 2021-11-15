package ru.cinimex.deveducate.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cinimex.deveducate.rest.dto.LifeDto;

import java.util.Date;


@RestController
@RequestMapping("/")
public class LifeController {

    @GetMapping(path = "/alive", produces = "application/json")
    public ResponseEntity<LifeDto> alive(){

        return new ResponseEntity<>(new LifeDto(true, new Date()), HttpStatus.OK);
    }

}
