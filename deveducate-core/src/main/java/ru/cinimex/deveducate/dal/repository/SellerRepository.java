package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.SellerEntity;

import java.util.Date;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {

    //@Query(value = "SELECT * FROM sellers ", nativeQuery = true)
    //List<SellerEntity> findAll();

    @Modifying
    @Query("update SellerEntity s set s.expiresOn = :updateOn where s.sellerId = :sellerId")
    int updateSellerSetExpiresOn(@Param("updateOn") Date updateOn,
                                 @Param("sellerId") Integer sellerId);
}
