package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.CustomerDto;

import java.util.List;

@Service
public interface CustomerService {

    CustomerDto get(int id);

    CustomerDto save(CustomerDto customerDto);

    List<CustomerDto> getAll();

    CustomerDto update(CustomerDto customerDto);

    void remove(int id);
}
