package ru.cinimex.deveducate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.configuration.ConfigurableMapperOrika;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.repository.CustomerRepository;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import ru.cinimex.deveducate.service.ConvertObject;
import ru.cinimex.deveducate.service.CustomerService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService, ConvertObject<CustomerEntity, CustomerDto> {

    private final ConfigurableMapperOrika mapperFactory;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto get(int id) {
        CustomerEntity customerEntityOpt = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return objectEntityMapsToObjectDto(customerEntityOpt);
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {

        CustomerEntity customerEntity = objectDtoMapsToObjectEntity(customerDto);
        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);

        return objectEntityMapsToObjectDto(newCustomerEntity);
    }

    @Override
    public List<CustomerDto> getAll() {
        Iterable<CustomerEntity> customerEntityList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();

        for (CustomerEntity customer : customerEntityList) {
            customerDtoList.add(objectEntityMapsToObjectDto(customer));
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {

        int id = customerRepository.updateCustomerSetName(customerDto.getId(), customerDto.getFirstName(), customerDto.getLastName());
        if (id > 0) {
            return customerDto;
        } else {
            throw new EntityNotFoundException("Объект не найден!");
        }
    }

    @Override
    public void remove(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDto objectEntityMapsToObjectDto(CustomerEntity objectEntity) {

        return mapperFactory.map(objectEntity, CustomerDto.class);
    }
    @Override
    public CustomerEntity objectDtoMapsToObjectEntity(CustomerDto objectDto) {

        return mapperFactory.map(objectDto, CustomerEntity.class);
    }

}
