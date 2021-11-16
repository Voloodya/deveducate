package ru.cinimex.deveducate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.configuration.ConfigurableMapperOrika;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.repository.CustomerRepository;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.CustomerService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final ConfigurableMapperOrika mapperFactory;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto get(int id) {
        CustomerEntity customerEntityOpt = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        CustomerDto customerDto = objectEntityMapsToObjectDto(customerEntityOpt);

        return customerDto;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {

        CustomerEntity customerEntity = objectDtoMapsToObjectEntity(customerDto);
        customerRepository.save(customerEntity);
        customerDto.setId(customerEntity.getCustomerId());

        return customerDto;
    }

    @Override
    public List<CustomerDto> getAll() {
        Iterable<CustomerEntity> customerEntityList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = null;

        if (customerEntityList != null) {
            customerDtoList = new ArrayList<CustomerDto>();
            for (CustomerEntity customer : customerEntityList) {
                customerDtoList.add(objectEntityMapsToObjectDto(customer));
            }
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new EntityNotFoundException());
        if (customerEntity != null) {
            customerRepository.save(customerEntity);
            // ...
        }
        return customerDto;
    }

    @Override
    public void remove(int id) {
        try {
            customerRepository.deleteById(id);
        }catch (EntityNotFoundException ex){
            new EntityNotFoundException();
        }
    }


    public CustomerDto objectEntityMapsToObjectDto(CustomerEntity objectEntity) {
        CustomerDto customerDto = mapperFactory.map(objectEntity, CustomerDto.class);

        return customerDto;
    }

    public CustomerEntity objectDtoMapsToObjectEntity(CustomerDto objectDto) {
        CustomerEntity customerEntity = mapperFactory.map(objectDto, CustomerEntity.class);

        return customerEntity;
    }

}
