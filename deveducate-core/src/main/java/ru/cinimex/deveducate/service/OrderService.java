package ru.cinimex.deveducate.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.OrderDto;

import java.util.List;

@Service
public interface OrderService {

    OrderDto get(int id);

    OrderDto save(OrderDto orderDto);

    List<OrderDto> getAll();

    Page<OrderDto> getPage(Pageable pageable);

    OrderDto update(OrderDto orderDto);

    void remove(int id);

    Page<OrderDto> getSpecificPage(Pageable pageable, int orderTotal);

    List<OrderDto> getByCurrentDate();

    List<OrderDto> getByCustomer(Integer id);

    List<OrderDto> getByOrderTotal(int count);
}
