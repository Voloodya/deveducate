package ru.cinimex.deveducate.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.rest.dto.CustomerDto;


@Service
public class CustomerServiceImpl implements CustomerService {

    private MapperFactory mapperFactory;

    public CustomerServiceImpl(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Override
    public CustomerDto customerEntityMapsToCustomerDto(CustomerEntity customerEntity) {
        mapperFactory.classMap(CustomerEntity.class, CustomerDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        CustomerDto customerDto = mapper.map(customerEntity, CustomerDto.class);

        return customerDto;
    }

    @Override
    public CustomerEntity customerDtoMapsToCustomerEntity(CustomerDto customerDto) {
        mapperFactory.classMap(CustomerDto.class, CustomerEntity.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        CustomerEntity customerEntity = mapper.map(customerDto, CustomerEntity.class);

        return customerEntity;
    }
}
