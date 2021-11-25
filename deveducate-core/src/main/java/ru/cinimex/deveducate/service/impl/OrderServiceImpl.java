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

    private static final int NUMBERTOTAL = 10;
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final ConfigurableMapperOrika mapperFactory;
    private final OrderRepository orderRepository;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public OrderDto get(int id) {
        OrderEntity orderEntityOpt = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return objectEntityMapsToObjectDto(orderEntityOpt);
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
            orderEntity.setOrderTotal(NUMBERTOTAL);
        }
        OrderEntity newOrderEntity = orderRepository.save(orderEntity);

        return objectEntityMapsToObjectDto(newOrderEntity);
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
    public List<OrderDto> getByCurrentDate() {
        QOrderEntity qOrder = QOrderEntity.orderEntity;
        Date date = new Date();
        BooleanExpression isCurrentDate = qOrder.orderTimestamp.between(date, DateUtils.addDays(date, 1));
        Iterable<OrderEntity> orderEntityList = orderRepository.findAll(isCurrentDate);

        return mapperFactory.mapAsList(orderEntityList, OrderDto.class);
    }

    @Override
    public List<OrderDto> getByCustomer(Integer id) {
        QOrderEntity qOrder = QOrderEntity.orderEntity;
        BooleanExpression isCustomer = qOrder.customer.customerId.eq(id);
        List<OrderEntity> orderEntityList = null;
        try {
            orderEntityList = (List<OrderEntity>) orderRepository.findAll(isCustomer);
        } catch (NullPointerException ex) {
            logger.error("Исключение в методе getByCustomer: ", ex);
            throw new NullPointerException();
        }

        return mapperFactory.mapAsList(orderEntityList, OrderDto.class);
    }

    @Override
    public List<OrderDto> getByOrderTotal(int count) {
        List<OrderEntity> orderEntityList = orderRepository.findByOrderTotal(count);

        return mapperFactory.mapAsList(orderEntityList, OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        // Для update через save: "OrderEntity orderEntity = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new EntityNotFoundException());"
        int count = orderRepository.updateOrderSetOrderTotal(orderDto.getOrderTotal(), orderDto.getId());
        if (count > 0) {
            return orderDto;
        } else {
            throw new EntityNotFoundException();
        }
    }


    @Override
    public void remove(int id) {
        try {
            orderRepository.deleteById(id);
        }catch (Exception ex){
            logger.error("Error in OrderServiceImpl: ", ex);
        }
    }

    public OrderDto objectEntityMapsToObjectDto(OrderEntity objectEntity) {

        return mapperFactory.map(objectEntity, OrderDto.class);
    }

    public OrderEntity objectDtoMapsToObjectEntity(OrderDto objectDto) {

        return mapperFactory.map(objectDto, OrderEntity.class);
    }

}
