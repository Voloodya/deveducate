package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

@Service
public interface CustomerService {

    public CustomerDto customerEntityMapsToCustomerDto(CustomerEntity customerEntity);

    public  CustomerEntity customerDtoMapsToCustomerEntity(CustomerDto customerDto);
}
