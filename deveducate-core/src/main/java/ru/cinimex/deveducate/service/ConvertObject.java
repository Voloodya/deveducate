package ru.cinimex.deveducate.service;

import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

public interface ConvertObject <T, J> {

    J objectEntityMapsToObjectDto(T objectEntity);
    T objectDtoMapsToObjectEntity(J objectDto);
}
