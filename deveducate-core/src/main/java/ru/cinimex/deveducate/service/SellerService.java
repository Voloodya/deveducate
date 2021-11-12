package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.SellerDto;

import java.util.List;


@Service
public interface SellerService {

    public SellerDto get(int id);

    public SellerDto save(SellerDto sellerDto);

    public List<SellerDto> getAll();

    public SellerDto update(SellerDto sellerDto);

    public void remove(int id);
}
