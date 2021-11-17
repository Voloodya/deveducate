package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    //@Query(value = "SELECT * FROM customers", nativeQuery = true)
    //List<CustomerEntity> findAll();
    @Modifying
    @Query("update CustomerEntity c set c.phoneNumber2 = :phoneNumber2 where c.customerId = :customerId")
    int updateCustomerSetPhoneNumber2(@Param("phoneNumber2") String phoneNumber2,
                                      @Param("customerId") Integer customerId);

}
