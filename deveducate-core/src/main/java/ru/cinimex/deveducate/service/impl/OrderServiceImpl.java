package ru.cinimex.deveducate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.configuration.ConfigurableMapperOrika;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.dal.repository.OrderRepository;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.service.ConvertObject;
import ru.cinimex.deveducate.service.OrderService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService, ConvertObject<OrderEntity, OrderDto> {

    private final ConfigurableMapperOrika mapperFactory;
    private final OrderRepository orderRepository;


    @Override
    public OrderDto get(int id) {
        OrderEntity orderEntityOpt = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        OrderDto orderDto = objectEntityMapsToObjectDto(orderEntityOpt);

        return orderDto;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = objectDtoMapsToObjectEntity(orderDto);
        orderRepository.save(orderEntity);
        orderDto.setOrderId(orderEntity.getOrderId());

        return orderDto;
    }

    @Override
    public List<OrderDto> getAll() {
        Iterable<OrderEntity> orderEntityList = orderRepository.findAll();
        List<OrderDto> orderDtoList = null;

        if (orderEntityList != null) {
            orderDtoList = new ArrayList<OrderDto>();
            for (OrderEntity order : orderEntityList) {
                orderDtoList.add(objectEntityMapsToObjectDto(order));
            }
        }
        return orderDtoList;
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.findById(orderDto.getOrderId()).orElseThrow(() -> new EntityNotFoundException());
        if (orderEntity != null) {
            orderRepository.save(orderEntity);
            // ...
        }
        return orderDto;
    }

    @Override
    public void remove(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto objectEntityMapsToObjectDto(OrderEntity objectEntity) {
        OrderDto orderDto = mapperFactory.map(objectEntity, OrderDto.class);

        return orderDto;
    }

    @Override
    public OrderEntity objectDtoMapsToObjectEntity(OrderDto objectDto) {
        OrderEntity orderEntity = mapperFactory.map(objectDto, OrderEntity.class);

        return orderEntity;
    }

}
