package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cinimex.deveducate.dal.entity.CustomerEntity;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Modifying
    @Query("update CustomerEntity c set c.custFirstName = :firstName, c.custLastName = :lastName where c.customerId = :customerId")
    int updateCustomerSetName(@Param("customerId") Integer customerId,
                              @Param("firstName") String firstName,
                              @Param("lastName") String lastName);
}
