package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.SellerEntity;

@Repository
public interface SellerRepository extends CrudRepository<SellerEntity, Integer> {
}
