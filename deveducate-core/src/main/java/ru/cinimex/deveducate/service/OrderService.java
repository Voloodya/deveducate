package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.rest.dto.OrderDto;

import java.util.List;

@Service
public interface OrderService {

    public OrderDto  orderEntityMapsToOrderDto(OrderEntity orderEntity);

    public  OrderEntity orderDtoMapsToOrderEntity(OrderDto orderDto);
}
