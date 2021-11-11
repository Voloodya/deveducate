package ru.cinimex.deveducate.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cinimex.deveducate.rest.dto.LifeDto;

import java.util.Date;


@RestController
@RequestMapping("/")
public class LifeController {

    @GetMapping(path = "/alive", produces = "application/json")
    public LifeDto alive(){

        LifeDto life = new LifeDto(true, new Date());

        return life;
    }

}
