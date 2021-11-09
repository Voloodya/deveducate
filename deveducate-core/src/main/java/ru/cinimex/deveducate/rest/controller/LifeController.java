package ru.cinimex.deveducate.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cinimex.deveducate.rest.DTO.Life;

import java.util.Date;


@RestController
@RequestMapping("/")
public class LifeController {

    @GetMapping(path = "/alive", produces = "application/json")
    public Life alive(){

        Life life = new Life(true, new Date());

        return life;
    }

}
