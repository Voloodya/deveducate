package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.OrderDto;

import java.util.List;

@Service
public interface OrderService {

    OrderDto get(int id);

    OrderDto save(OrderDto orderDto);

    List<OrderDto> getAll();

    OrderDto update(OrderDto orderDto);

    void remove(int id);
}
