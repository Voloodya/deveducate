package ru.cinimex.deveducate.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.OrderDto;

import java.util.List;

@Service
public interface OrderService {

    OrderDto get(int id);

    OrderDto save(OrderDto orderDto);

    List<OrderDto> getAll();

    Page<OrderDto> getAllPage(Pageable pageable);

    Slice<OrderDto> getAllSlicePage(Pageable pageable);

    OrderDto update(OrderDto orderDto);

    void remove(int id);
}
