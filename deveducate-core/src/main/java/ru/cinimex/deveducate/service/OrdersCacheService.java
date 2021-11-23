package ru.cinimex.deveducate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.repository.OrderRepository;
import ru.cinimex.deveducate.rest.dto.CountDto;
import ru.cinimex.deveducate.rest.dto.OrderDto;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersCacheService {

    private List<OrderDto> data = new ArrayList<>();
    private Long count=0L;

    @Cacheable(value = "orders", key = "#id")
    public OrderDto get(Long id) {
        count++;
        return data.stream().filter(elem -> elem.getId().equals(id.intValue())).findAny().orElse(null);
    }

    @CacheEvict(value = "orders", allEntries = true)
    public void clear() {
    }

    public void put(OrderDto orderDto) {
        data.add(orderDto);
    }

    public CountDto getCount(){

        return new CountDto(count);
    }
}
