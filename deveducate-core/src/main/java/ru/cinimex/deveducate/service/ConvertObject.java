package ru.cinimex.deveducate.service;

import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.rest.dto.OrderDto;

public interface ConvertObject<T, J> {

    public J objectEntityMapsToObjectDto(T objectEntity);

    public T objectDtoMapsToObjectEntity(J objectDto);
}
