package ru.cinimex.deveducate.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.entity.OrderEntity;
import ru.cinimex.deveducate.dal.repository.CustomerRepository;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService, ConvertObject<CustomerEntity, CustomerDto> {

    private MapperFactory mapperFactory;

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Override
    public CustomerDto get(int id) {
        Optional<CustomerEntity> customerEntityOpt = customerRepository.findById(id);
        CustomerDto customerDto = customerEntityOpt.isPresent() ? objectEntityMapsToObjectDto(customerEntityOpt.get()) : null;

        return customerDto;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {

        CustomerEntity customerEntity = objectDtoMapsToObjectEntity(customerDto);
        customerRepository.save(customerEntity);
        customerDto.setCustomerId(customerEntity.getCustomerId());

        return customerDto;
    }

    @Override
    public List<CustomerDto> getAll() {
        Iterable<CustomerEntity> customerEntityList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = null;

        if(customerEntityList != null) {
            customerDtoList = new ArrayList<CustomerDto>();
            for (CustomerEntity customer : customerEntityList) {
                customerDtoList.add(objectEntityMapsToObjectDto(customer));
            }
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Optional<CustomerEntity> customerEntityOpt = customerRepository.findById(customerDto.getCustomerId());
        CustomerEntity customerEntity = customerEntityOpt.isPresent() ? customerEntityOpt.get() : null;
        if(customerEntity != null) {
            customerRepository.save(customerEntity);
            // ...
        }
        return customerDto;
    }

    @Override
    public void remove(int id) {
        customerRepository.deleteById(id);
    }


    @Override
    public CustomerDto objectEntityMapsToObjectDto(CustomerEntity objectEntity) {
        mapperFactory.classMap(CustomerEntity.class, CustomerDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        CustomerDto customerDto = mapper.map(objectEntity, CustomerDto.class);

        return customerDto;
    }

    @Override
    public CustomerEntity objectDtoMapsToObjectEntity(CustomerDto objectDto) {
        mapperFactory.classMap(CustomerDto.class, CustomerEntity.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        CustomerEntity customerEntity = mapper.map(objectDto, CustomerEntity.class);

        return customerEntity;
    }
}
