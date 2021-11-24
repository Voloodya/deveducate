package ru.cinimex.deveducate.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.configuration.ConfigurableMapperOrika;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.dal.entity.QOrderEntity;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.dal.repository.CustomerRepository;
import ru.cinimex.deveducate.dal.repository.OrderRepository;
import ru.cinimex.deveducate.dal.repository.SellerRepository;
import ru.cinimex.deveducate.rest.dto.OrderDto;
import ru.cinimex.deveducate.rest.filter.OrderSpecification;
import ru.cinimex.deveducate.service.OrderService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final static int numberTotal = 10;
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
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
        if (orderEntity.getCustomer() != null && orderEntity.getSeller() != null) {
            SellerEntity sellerEntity = sellerRepository.findById(orderDto.getSeller().getId()).orElseThrow(() -> new EntityNotFoundException());
            CustomerEntity customerEntity = customerRepository.findById(orderDto.getCustomer().getId()).orElseThrow(() -> new EntityNotFoundException());
            orderEntity.setSeller(sellerEntity);
            orderEntity.setCustomer(customerEntity);
        }
        if (orderDto.getOrderTotal() == null) {
            orderEntity.setOrderTotal(numberTotal);
        }
        orderRepository.save(orderEntity);
        orderDto.setId(orderEntity.getOrderId());

        return orderDto;
    }

    @Override
    public List<OrderDto> getAll() {
        Iterable<OrderEntity> orderEntityList = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderEntity order : orderEntityList) {
            orderDtoList.add(objectEntityMapsToObjectDto(order));
        }
        return orderDtoList;
    }

    @Override
    public Page<OrderDto> getPage(Pageable pageable) {
        Page<OrderEntity> orderEntityPage = orderRepository.findAll(pageable);
        List<OrderEntity> orderEntityList = orderEntityPage.getContent();
        List<OrderDto> orderDtoList = orderEntityList.stream().map(o -> objectEntityMapsToObjectDto(o)).collect(Collectors.toList());

        return new PageImpl<>(orderDtoList, pageable, orderEntityPage.getTotalElements());
    }


    @Override
    public Page<OrderDto> getSpecificPage(Pageable pageable, int orderTotal) {
        Page<OrderEntity> orderEntityPage = orderRepository.findAll(Specification.where(OrderSpecification.isOrderTotal(orderTotal)), pageable);
        List<OrderEntity> orderEntityList = orderEntityPage.getContent();
        List<OrderDto> orderDtoList = orderEntityList.stream().map(o -> objectEntityMapsToObjectDto(o)).collect(Collectors.toList());

        return new PageImpl<>(orderDtoList, pageable, orderEntityPage.getTotalElements());
    }

    @Override
    public List<OrderDto> getCurrentDate() {
        QOrderEntity qOrder = QOrderEntity.orderEntity;
        Date date = new Date();
        BooleanExpression isCurrentDate = qOrder.orderTimestamp.between(date, DateUtils.addDays(new Date(), 1));
        Iterable<OrderEntity> orderEntityList = orderRepository.findAll(isCurrentDate);
        List<OrderDto> orderDtoList = mapperFactory.mapAsList(orderEntityList, OrderDto.class);

        return orderDtoList;
    }

    @Override
    public List<OrderDto> getByCustomer(Integer id) {
        QOrderEntity qOrder = QOrderEntity.orderEntity;
        BooleanExpression isCustomer = qOrder.customer.customerId.eq(id);
        List<OrderEntity> orderEntityList = null;
        try {
            orderEntityList = (List<OrderEntity>) orderRepository.findAll(isCustomer);
        } catch (Exception ex) {
            logger.error("Исключение в методе getByCustomer: ", ex);
        }
        List<OrderDto> orderDtoList = mapperFactory.mapAsList(orderEntityList, OrderDto.class);

        return orderDtoList;
    }

    @Override
    public List<OrderDto> getByOrderTotal(int count) {
        List<OrderEntity> orderEntityList = orderRepository.findByOrderTotal(count);
        List<OrderDto> orderDtoList = mapperFactory.mapAsList(orderEntityList, OrderDto.class);

        return orderDtoList;
    }


    @Override
    public OrderDto update(OrderDto orderDto) {
        //OrderEntity orderEntity = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new EntityNotFoundException());
        int count = orderRepository.updateOrderSetOrderTotal(orderDto.getOrderTotal(), orderDto.getId());
        if (count > 0) {
            return orderDto;
        } else {
            return null;
        }
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
