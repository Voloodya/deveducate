package ru.cinimex.deveducate.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.dal.repository.OrderRepository;
import ru.cinimex.deveducate.rest.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService, ConvertObject<OrderEntity, OrderDto>{

    private MapperFactory mapperFactory;

    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Override
    public OrderDto get(int id) {
        Optional<OrderEntity> orderEntityOpt = orderRepository.findById(id);
        OrderDto orderDto = orderEntityOpt.isPresent() ? objectEntityMapsToObjectDto(orderEntityOpt.get()) : null;

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

        if(orderEntityList != null) {
            orderDtoList = new ArrayList<OrderDto>();
            for (OrderEntity order : orderEntityList) {
                orderDtoList.add(objectEntityMapsToObjectDto(order));
            }
        }
        return orderDtoList;
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Optional<OrderEntity> orderEntityOpt = orderRepository.findById(orderDto.getOrderId());
        OrderEntity orderEntity = orderEntityOpt.isPresent() ? orderEntityOpt.get() : null;
        if(orderEntity != null) {
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
        mapperFactory.classMap(OrderEntity.class, OrderDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        OrderDto orderDto = mapper.map(objectEntity, OrderDto.class);

        return orderDto;
    }

    @Override
    public OrderEntity objectDtoMapsToObjectEntity(OrderDto objectDto) {
        mapperFactory.classMap(OrderDto.class, OrderEntity.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        OrderEntity orderEntity = mapper.map(objectDto, OrderEntity.class);

        return orderEntity;
    }
}
