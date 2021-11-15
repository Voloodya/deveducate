package ru.cinimex.deveducate.service;

import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.rest.dto.OrderDto;

public interface ConvertObject<T, J> {

    J objectEntityMapsToObjectDto(T objectEntity);

    T objectDtoMapsToObjectEntity(J objectDto);
}
