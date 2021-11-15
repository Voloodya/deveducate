package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.rest.dto.SellerDto;

import java.util.List;


@Service
public interface SellerService {

    SellerDto get(int id);

    SellerDto save(SellerDto sellerDto);

    List<SellerDto> getAll();

    SellerDto update(SellerDto sellerDto);

    void remove(int id);
}
