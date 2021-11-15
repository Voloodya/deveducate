package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.SellerEntity;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {

    //@Query(value = "SELECT * FROM sellers ", nativeQuery = true)
    //List<SellerEntity> findAll();
}
