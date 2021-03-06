package ru.cinimex.deveducate.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.cinimex.deveducate.rest.dto.CountDto;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.service.OrdersCacheService;

@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping("/cache")
public class OrderCacheController {

    private final OrdersCacheService ordersCacheService;

    @GetMapping(path = "{id}")
    public OrderDto get(@PathVariable(value = "id") Long id) {

        OrderDto orderDto = ordersCacheService.get(id);
        return orderDto;
    }

    @DeleteMapping(path = "/clear")
    public void clear() {

        ordersCacheService.clear();
    }

    @PostMapping
    public void put(@RequestBody OrderDto orderDto) {

        ordersCacheService.put(orderDto);

    }

    @GetMapping("/count")
    public CountDto count() {

        return ordersCacheService.getCount();
    }
}
