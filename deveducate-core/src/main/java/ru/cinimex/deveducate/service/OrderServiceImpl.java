package ru.cinimex.deveducate.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.rest.dto.OrderDto;


@Service
public class OrderServiceImpl implements OrderService{

    private MapperFactory mapperFactory;

    public OrderServiceImpl(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Override
    public OrderDto orderEntityMapsToOrderDto(OrderEntity orderEntity) {
        mapperFactory.classMap(OrderEntity.class, OrderDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        OrderDto orderDto = mapper.map(orderEntity, OrderDto.class);

        return orderDto;
    }

    @Override
    public OrderEntity orderDtoMapsToOrderEntity(OrderDto orderDto) {
        mapperFactory.classMap(OrderDto.class, OrderEntity.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        OrderEntity orderEntity = mapper.map(orderDto, OrderEntity.class);

        return orderEntity;
    }
}
