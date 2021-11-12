package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.OrderDto;

import java.util.List;

@Service
public interface OrderService {

    public OrderDto get(int id);

    public OrderDto save(OrderDto orderDto);

    public List<OrderDto> getAll();

    public OrderDto update(OrderDto orderDto);

    public void remove(int id);
}
