package ru.cinimex.deveducate.service;

import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.rest.dto.SellerDto;


@Service
public interface SellerService {

    public SellerDto sellerEntityMapsToSellerDto(SellerEntity sellerEntity);

    public  SellerEntity sellerDtoMapsToSellerEntity(SellerDto SellerDto);
}
