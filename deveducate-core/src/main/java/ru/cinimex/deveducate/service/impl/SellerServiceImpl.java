package ru.cinimex.deveducate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cinimex.deveducate.configuration.ConfigurableMapperOrika;
import ru.cinimex.deveducate.dal.entity.SellerEntity;
import ru.cinimex.deveducate.dal.repository.SellerRepository;
import ru.cinimex.deveducate.rest.dto.SellerDto;
import ru.cinimex.deveducate.service.SellerService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SellerServiceImpl implements SellerService {

    private final ConfigurableMapperOrika mapperFactory;
    private final SellerRepository sellerRepository;

    @Override
    public SellerDto get(int id) {
        SellerEntity sellerEntityOpt = sellerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        SellerDto sellerDto = objectEntityMapsToObjectDto(sellerEntityOpt);

        return sellerDto;
    }

    @Override
    public SellerDto save(SellerDto sellerDto) {
        SellerEntity sellerEntity = objectDtoMapsToObjectEntity(sellerDto);
        sellerRepository.save(sellerEntity);
        sellerDto.setId(sellerEntity.getSellerId());

        return sellerDto;
    }

    @Override
    public List<SellerDto> getAll() {
        Iterable<SellerEntity> sellerEntityList = sellerRepository.findAll();
        List<SellerDto> sellerDtoList = new ArrayList<>();

        for (SellerEntity seller : sellerEntityList) {
            sellerDtoList.add(objectEntityMapsToObjectDto(seller));
        }
        return sellerDtoList;
    }

    @Override
    public SellerDto update(SellerDto sellerDto) {
        //SellerEntity sellerEntity = sellerRepository.findById(sellerDto.getId()).orElseThrow(() -> new EntityNotFoundException());
        Date date = sellerDto.getUpdateOn() != null ? sellerDto.getUpdateOn() : new Date();
        sellerRepository.updateSellerSetExpiresOnAndName(sellerDto.getId(), date, sellerDto.getName());
        SellerEntity sellerEntity = sellerRepository.findById(sellerDto.getId()).orElseThrow(() -> new EntityNotFoundException());
        return objectEntityMapsToObjectDto(sellerEntity);
    }

    @Override
    public void remove(int id) {
        sellerRepository.deleteById(id);
    }

    public SellerDto objectEntityMapsToObjectDto(SellerEntity objectEntity) {
        SellerDto sellerDto = mapperFactory.map(objectEntity, SellerDto.class);

        return sellerDto;
    }

    public SellerEntity objectDtoMapsToObjectEntity(SellerDto objectDto) {
        SellerEntity sellerEntity = mapperFactory.map(objectDto, SellerEntity.class);

        return sellerEntity;
    }

}
