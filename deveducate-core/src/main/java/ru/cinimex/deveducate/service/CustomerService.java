package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.CustomerDto;
import java.util.List;

@Service
public interface CustomerService {

    public CustomerDto get(int id);

    public CustomerDto save(CustomerDto customerDto);

    public List<CustomerDto> getAll();

    public CustomerDto update(CustomerDto customerDto);

    public void remove(int id);
}
