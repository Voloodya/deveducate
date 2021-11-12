package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;
import ru.cinimex.deveducate.dal.entity.SellerEntity;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    @Query(value = "SELECT * FROM customers", nativeQuery = true)
    List<CustomerEntity> findAll();

}
