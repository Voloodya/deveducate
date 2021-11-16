package ru.cinimex.deveducate.service;

public interface ConvertObject<T, J> {

    J objectEntityMapsToObjectDto(T objectEntity);

    T objectDtoMapsToObjectEntity(J objectDto);
}
