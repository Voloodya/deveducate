package ru.cinimex.deveducate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.configuration.ConfigurableMapperOrika;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.dal.repository.CustomerRepository;
import ru.cinimex.deveducate.dal.repository.OrderRepository;
import ru.cinimex.deveducate.dal.repository.SellerRepository;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.service.OrderService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final ConfigurableMapperOrika mapperFactory;
    private final OrderRepository orderRepository;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;


    @Override
    public OrderDto get(int id) {
        OrderEntity orderEntityOpt = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        OrderDto orderDto = objectEntityMapsToObjectDto(orderEntityOpt);

        return orderDto;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = objectDtoMapsToObjectEntity(orderDto);
        SellerEntity sellerEntity = sellerRepository.findById(orderDto.getSeller().getId()).orElseThrow(() -> new EntityNotFoundException());
        CustomerEntity customerEntity = customerRepository.findById(orderDto.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException());
        orderEntity.setSeller(sellerEntity);
        orderEntity.setCustomer(customerEntity);
        orderEntity.setOrderTotal(10);
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
    public Page<OrderDto> getAllPage(Pageable pageable) {
        return null;
    }

    @Override
    public Slice<OrderDto> getAllSlicePage(Pageable pageable) {
        return null;
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

    public OrderDto objectEntityMapsToObjectDto(OrderEntity objectEntity) {
        OrderDto orderDto = mapperFactory.map(objectEntity, OrderDto.class);

        return orderDto;
    }

    public OrderEntity objectDtoMapsToObjectEntity(OrderDto objectDto) {
        OrderEntity orderEntity = mapperFactory.map(objectDto, OrderEntity.class);

        return orderEntity;
    }

}
