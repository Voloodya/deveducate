package ru.cinimex.deveducate.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
    private final ConfigurableMapperOrika mapperFactory;
    private final SellerRepository sellerRepository;

    @Override
    public SellerDto get(int id) {
        SellerEntity sellerEntityOpt = sellerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return objectEntityMapsToObjectDto(sellerEntityOpt);
    }

    @Override
    public SellerDto save(SellerDto sellerDto) {
        SellerEntity sellerEntity = objectDtoMapsToObjectEntity(sellerDto);
        SellerEntity newSellerEntity = sellerRepository.save(sellerEntity);

        return objectEntityMapsToObjectDto(newSellerEntity);
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
        // //SellerEntity sellerEntity = sellerRepository.findById(sellerDto.getId()).orElseThrow(() -> new EntityNotFoundException());
        Date date = sellerDto.getUpdateOn() != null ? sellerDto.getUpdateOn() : new Date();
        sellerRepository.updateSellerSetExpiresOnAndName(sellerDto.getId(), date, sellerDto.getName());
        SellerEntity sellerEntity = sellerRepository.findById(sellerDto.getId()).orElseThrow(() -> new EntityNotFoundException());
        return objectEntityMapsToObjectDto(sellerEntity);
    }

    @Override
    public void remove(int id) {
        try {
            sellerRepository.deleteById(id);
        }catch (Exception ex){
            logger.error("Error in the class SellerServiceImpl: ",ex);
            throw new EntityNotFoundException();
        }
    }

    public SellerDto objectEntityMapsToObjectDto(SellerEntity objectEntity) {

        return mapperFactory.map(objectEntity, SellerDto.class);
    }

    public SellerEntity objectDtoMapsToObjectEntity(SellerDto objectDto) {

        return mapperFactory.map(objectDto, SellerEntity.class);
    }

}
