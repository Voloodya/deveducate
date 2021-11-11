package ru.cinimex.deveducate.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.rest.dto.SellerDto;


@Service
public class SellerServiceImpl implements SellerService{

    private MapperFactory mapperFactory;

    public SellerServiceImpl(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }


    @Override
    public SellerDto sellerEntityMapsToSellerDto(SellerEntity sellerEntity) {
        mapperFactory.classMap(SellerEntity.class, SellerDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SellerDto sellerDto = mapper.map(sellerEntity, SellerDto.class);

        return sellerDto;
    }

    @Override
    public SellerEntity sellerDtoMapsToSellerEntity(SellerDto sellerDto) {
        mapperFactory.classMap(SellerDto.class, SellerEntity.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SellerEntity sellerEntity = mapper.map(sellerDto, SellerEntity.class);

        return sellerEntity;
    }
}
