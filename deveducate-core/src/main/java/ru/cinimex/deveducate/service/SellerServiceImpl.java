package ru.cinimex.deveducate.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.dal.repository.SellerRepository;
import ru.cinimex.deveducate.rest.dto.SellerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SellerServiceImpl implements SellerService, ConvertObject<SellerEntity, SellerDto>{

    private MapperFactory mapperFactory;

    private SellerRepository sellerRepository;

    @Override
    public SellerDto get(int id) {
        Optional<SellerEntity> sellerEntityOpt = sellerRepository.findById(id);
        SellerDto sellerDto = sellerEntityOpt.isPresent() ? objectEntityMapsToObjectDto(sellerEntityOpt.get()) : null;

        return sellerDto;
    }

    @Override
    public SellerDto save(SellerDto sellerDto) {
        SellerEntity sellerEntity = objectDtoMapsToObjectEntity(sellerDto);
        sellerRepository.save(sellerEntity);
        sellerDto.setSellerId(sellerEntity.getSellerId());

        return sellerDto;
    }

    @Override
    public List<SellerDto> getAll() {
        Iterable<SellerEntity> sellerEntityList = sellerRepository.findAll();
        List<SellerDto> sellerDtoList = null;

        if(sellerEntityList != null) {
            sellerDtoList = new ArrayList<SellerDto>();
            for (SellerEntity seller : sellerEntityList) {
                sellerDtoList.add(objectEntityMapsToObjectDto(seller));
            }
        }
        return sellerDtoList;
    }

    @Override
    public SellerDto update(SellerDto sellerDto) {
        Optional<SellerEntity> sellerEntityOpt = sellerRepository.findById(sellerDto.getSellerId());
        SellerEntity sellerEntity = sellerEntityOpt.isPresent() ? sellerEntityOpt.get() : null;
        if(sellerEntity != null) {
            sellerRepository.save(sellerEntity);
            // ...
        }
        return sellerDto;
    }

    @Override
    public void remove(int id) {
        sellerRepository.deleteById(id);
    }

    @Override
    public SellerDto objectEntityMapsToObjectDto(SellerEntity objectEntity) {
        mapperFactory.classMap(SellerEntity.class, SellerDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SellerDto sellerDto = mapper.map(objectEntity, SellerDto.class);

        return sellerDto;
    }

    @Override
    public SellerEntity objectDtoMapsToObjectEntity(SellerDto objectDto) {
        mapperFactory.classMap(SellerDto.class, SellerEntity.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SellerEntity sellerEntity = mapper.map(objectDto, SellerEntity.class);

        return sellerEntity;
    }
}
